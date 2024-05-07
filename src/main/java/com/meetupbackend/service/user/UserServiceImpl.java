package com.meetupbackend.service.user;

import com.meetupbackend.entity.user.User;
import com.meetupbackend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.callback.PasswordCallback;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository ;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository){
        userRepository = theUserRepository;
        passwordEncoder =  new BCryptPasswordEncoder();
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
    public User save(Map<String,String> reqBody) {
        String encodedPassword = passwordEncoder.encode(reqBody.get("password"));
        LocalDate date =  LocalDate.parse(reqBody.get("dob"));
        User user = new User(reqBody.get("firstName"),reqBody.get("lastName"),reqBody.get("email"),encodedPassword,date);
        System.out.println(encodedPassword+date);
        return userRepository.save(user);
    }

    @Override
    public void deleteById(int theId) {

    }
}
