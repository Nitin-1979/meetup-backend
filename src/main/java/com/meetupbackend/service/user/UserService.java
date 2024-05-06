package com.meetupbackend.service.user;

import com.meetupbackend.entity.user.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(int theId);

    User save(User theEmployee);

    void deleteById(int theId);
}
