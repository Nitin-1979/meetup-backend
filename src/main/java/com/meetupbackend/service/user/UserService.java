package com.meetupbackend.service.user;

import com.meetupbackend.entity.user.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> findAll();

    User findById(int theId);

    User save(Map<String,String> user);

    void deleteById(int theId);
}
