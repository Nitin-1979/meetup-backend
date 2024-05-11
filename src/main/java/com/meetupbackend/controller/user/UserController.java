package com.meetupbackend.controller.user;

import com.meetupbackend.entity.user.User;
import com.meetupbackend.service.user.UserService;
import com.meetupbackend.util.services.user.UserUtilService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserUtilService userUtilService;

    public UserController(UserUtilService userUtilService){
        this.userUtilService = userUtilService;

    }
    @GetMapping("/me")
    public ResponseEntity<User> me(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);

    }



}
