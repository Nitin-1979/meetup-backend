package com.meetupbackend.service.auth;

import com.meetupbackend.entity.user.User;
import com.meetupbackend.repository.user.UserRepository;
import com.meetupbackend.util.models.userDetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class AuthenticationService {
    private  UserRepository userRepository;

    private  PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;
    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;

    }
    public User signup(Map<String,String> input) {
        String encodedPassword = passwordEncoder.encode(input.get("password"));
        LocalDate date =  LocalDate.parse(input.get("dob"));
        User user = new User(input.get("firstName"),input.get("lastName"),input.get("email"),encodedPassword,date);
        return userRepository.save(user);
    }

    public UserDetails authenticate(Map<String,String> input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.get("email"),
                        input.get("password")
                )
        );
        return (UserDetails) userDetailsService.loadUserByUsername( input.get("email"));
    }
}
