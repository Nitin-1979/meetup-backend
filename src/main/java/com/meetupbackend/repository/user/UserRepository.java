package com.meetupbackend.repository.user;

import com.meetupbackend.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    // that's it ... no need to write any code LOL!

}
