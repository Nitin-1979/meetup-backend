package com.meetupbackend.service.JwtblacklistService;

import com.meetupbackend.entity.jwtBlacklist.JwtBlacklist;

import java.util.List;

public interface JwtblacklistService {
    List<String> findaAll();
    JwtBlacklist save(String jwt);

}
