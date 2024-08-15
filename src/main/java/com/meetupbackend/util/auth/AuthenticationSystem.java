package com.meetupbackend.util.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationSystem {
    public static Boolean isLogged() {
        System.out.println("===========================");
        System.out.println("called here");
        System.out.println("===========================");
        final Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        return null != authentication && !("anonymousUser").equals(authentication.getName());
    }
}
