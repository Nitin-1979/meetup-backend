package com.meetupbackend.controller.user;

import com.meetupbackend.entity.user.User;
import com.meetupbackend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
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

    @PostMapping("/users")
    public User addUser(@RequestBody Map<String,String> requestBody) {
        System.out.println(requestBody);
        User dbUser = userService.save(requestBody);
        return dbUser;
    }

}
