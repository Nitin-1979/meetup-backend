package com.meetupbackend.controller.auth;

import com.meetupbackend.entity.user.User;
import com.meetupbackend.service.JwtblacklistService.JwtblacklistService;
import com.meetupbackend.util.models.loginModel.LoginResponse;
import com.meetupbackend.service.auth.AuthenticationService;
import com.meetupbackend.util.models.userDetails.UserDetails;
import com.meetupbackend.util.services.jwt.JwtService;
import com.meetupbackend.util.auth.AuthenticationSystem;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {
    private JwtService jwtService;
    private JwtblacklistService jwtblacklistService;

    private AuthenticationService authenticationService;
    @Autowired
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, JwtblacklistService jwtblacklistService){
        this.jwtService = jwtService;
        this.jwtblacklistService = jwtblacklistService;
        this.authenticationService = authenticationService;
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse>  login(@RequestBody Map<String,String> requestBody, HttpServletResponse response){
        Boolean ans = AuthenticationSystem.isLogged();
        UserDetails registeredUser = authenticationService.authenticate(requestBody);
        String jwtToken = jwtService.generateToken(registeredUser);
        ResponseCookie cookie = ResponseCookie.from("jwtToken", jwtToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup")
    public User addUser(@RequestBody Map<String,String> requestBody) {
        Boolean ans = AuthenticationSystem.isLogged();
        User addedUser = authenticationService.signup(requestBody);
        return addedUser;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        String authHeader =null;
        if(request.getCookies() != null){
            for(Cookie cookie: request.getCookies()){
                if(cookie.getName().equals("jwtToken")){
                    authHeader = cookie.getValue();
                }
            }
        }
        jwtblacklistService.save(authHeader);
        return "Logged Out successfully";
    }
}
