package com.meetupbackend.service.user;

import com.meetupbackend.entity.user.User;
import com.meetupbackend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository ;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository){
        userRepository = theUserRepository;
    };
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int theId) {
        return null;
    }

    @Override
    public User save(User theEmployee) {
        return null;
    }

    @Override
    public void deleteById(int theId) {

    }
}
