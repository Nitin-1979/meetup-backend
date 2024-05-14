package com.meetupbackend.security.filters;

import com.meetupbackend.service.JwtblacklistService.JwtblacklistService;
import com.meetupbackend.util.services.jwt.JwtService;
//import com.meetupbackend.service.userDetails.UserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private  HandlerExceptionResolver handlerExceptionResolver;
    private JwtblacklistService jwtblacklistService;
    private  JwtService jwtService;
    private UserDetailsService userDetailsService;
    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserDetailsService userDetailsService,
            JwtblacklistService jwtblacklistService,
            HandlerExceptionResolver handlerExceptionResolver
    ) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.jwtblacklistService = jwtblacklistService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader =null;
        if(request.getCookies() != null){
            for(Cookie cookie: request.getCookies()){
                if(cookie.getName().equals("jwtToken")){
                    authHeader = cookie.getValue();
                }
            }
        }
        String requestURL = request.getRequestURL().toString();
        if(!(requestURL.contains("auth/login") || requestURL.contains("auth/signup"))){
            List<String> BlacklistedJwt = jwtblacklistService.findaAll();
            for (String str : BlacklistedJwt) {
                if (str.contains(authHeader)) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing JWT Token");
                    return;
                }
            }
        }

        if (authHeader == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader;
            final String userEmail = jwtService.extractUsername(jwt);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (userEmail != null && authentication == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}
