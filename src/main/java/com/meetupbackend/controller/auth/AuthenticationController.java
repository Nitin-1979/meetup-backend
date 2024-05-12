package com.meetupbackend.controller.auth;

import com.meetupbackend.entity.user.User;
import com.meetupbackend.util.models.loginModel.LoginResponse;
import com.meetupbackend.service.auth.AuthenticationService;
import com.meetupbackend.util.models.userDetails.UserDetails;
import com.meetupbackend.util.services.user.jwt.JwtService;
import com.meetupbackend.service.user.UserService;
import com.meetupbackend.util.auth.AuthenticationSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {
    private JwtService jwtService;

    private AuthenticationService authenticationService;
    @Autowired
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService){
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse>  login(@RequestBody Map<String,String> requestBody){
        Boolean ans = AuthenticationSystem.isLogged();
        UserDetails registeredUser = authenticationService.authenticate(requestBody);
        String jwtToken = jwtService.generateToken(registeredUser);
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

}
