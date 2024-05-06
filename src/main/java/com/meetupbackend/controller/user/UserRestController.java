package com.meetupbackend.controller.user;

import com.meetupbackend.entity.user.User;
import com.meetupbackend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController (UserService theUserService){
        userService = theUserService;
    }
    @GetMapping("/users")
    public List<User> getAll(){
        return userService.findAll();
    }


}
