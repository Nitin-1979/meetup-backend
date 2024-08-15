package com.meetupbackend.controller.user;

import com.meetupbackend.entity.user.User;
import com.meetupbackend.repository.user.UserRepository;
import com.meetupbackend.service.user.UserService;
import com.meetupbackend.util.models.userDetails.UserDetails;
import com.meetupbackend.util.services.user.UserUtilService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {
    private UserUtilService userUtilService;
    private UserRepository userRepository;

    public UserController(UserUtilService userUtilService,UserRepository userRepository){
        this.userUtilService = userUtilService;
        this.userRepository = userRepository;
    }
//    @GetMapping("/me")
//    public ResponseEntity<User> me(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Object principal = authentication.getPrincipal();
//        return ResponseEntity.ok(currentUser);
//    }

    @GetMapping("/me")
    public ResponseEntity<Optional<User>> me(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        Optional<User> currentUser = userRepository.findByEmail(user.getUsername());
        return ResponseEntity.ok(currentUser);

    }

    @GetMapping("/you")
    public String you(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails user = (UserDetails) authentication.getPrincipal();
//        System.out.println("principal in me "+user.getUsername());
        return "you";

    }



}
