package com.meetupbackend.repository.jwtblacklist;

import com.meetupbackend.entity.jwtBlacklist.JwtBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtblacklistRepository extends JpaRepository<JwtBlacklist, Integer> {
}
