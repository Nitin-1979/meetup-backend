package com.meetupbackend.controller.user;

import com.meetupbackend.entity.user.User;
import com.meetupbackend.service.user.UserService;
import com.meetupbackend.util.auth.AuthenticationSystem;
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
    @GetMapping("/login")
    public List<User> getAll(){
        Boolean ans = AuthenticationSystem.isLogged();
        System.out.println("ans"+ ans);
        return userService.findAll();
    }

    @PostMapping("/signup")
    public User addUser(@RequestBody Map<String,String> requestBody) {
        Boolean ans = AuthenticationSystem.isLogged();
        System.out.println("ans"+ ans);
//        System.out.println(requestBody);
        User dbUser = userService.save(requestBody);
        return dbUser;
    }

}
