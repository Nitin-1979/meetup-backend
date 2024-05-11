package com.meetupbackend.repository.user;

import com.meetupbackend.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    // that's it ... no need to write any code LOL!

}
