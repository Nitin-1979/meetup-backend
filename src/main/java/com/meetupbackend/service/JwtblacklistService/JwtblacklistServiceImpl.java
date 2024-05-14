package com.meetupbackend.service.JwtblacklistService;

import com.meetupbackend.entity.jwtBlacklist.JwtBlacklist;
import com.meetupbackend.entity.user.User;
import com.meetupbackend.repository.jwtblacklist.JwtblacklistRepository;
import com.meetupbackend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class JwtblacklistServiceImpl implements JwtblacklistService {
    private  JwtblacklistRepository jwtblacklistRepository ;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public JwtblacklistServiceImpl(JwtblacklistRepository jwtblacklistRepository){
        this.jwtblacklistRepository = jwtblacklistRepository;
    };

    @Override
    public List<String> findaAll() {
        List<JwtBlacklist> jwtBlacklist = jwtblacklistRepository.findAll();
        return jwtBlacklist.stream()
                .map(JwtBlacklist::getJwt)
                .collect(Collectors.toList());
    }

    @Override
    public JwtBlacklist save(String jwt) {
        JwtBlacklist jwtBlacklist = new JwtBlacklist(jwt);
        return jwtblacklistRepository.save(jwtBlacklist);
    }
}
