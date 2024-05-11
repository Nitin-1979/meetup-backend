package com.meetupbackend.util.services.user;

import com.meetupbackend.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserUtilService {
    private UserRepository userRepository;

    public UserUtilService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    //any function needed
}
