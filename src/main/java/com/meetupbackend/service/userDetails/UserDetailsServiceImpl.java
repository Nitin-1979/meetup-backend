package com.meetupbackend.service.userDetails;

import com.meetupbackend.entity.user.User;
import com.meetupbackend.util.models.userDetails.UserDetails;
import com.meetupbackend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Optional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;
    @Autowired
    public UserDetailsServiceImpl(UserRepository theUserRepository){
        userRepository = theUserRepository;
    };
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user  = userRepository.findByEmail(email);
        System.out.println("called loadUserByUsername"+ email);
        return user.map(UserDetails::new).orElseThrow(()->new UsernameNotFoundException("Invalid Username"));
    }
}
