package com.przemo.rentcar.controllers;

import lombok.Builder;
import lombok.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicAuthController {

    @GetMapping(path = "/basicauth")
    public AuthInfo basicAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthInfo authInfo = AuthInfo.builder()
                .email(authentication.getName())
                .role(authentication.getAuthorities().toString())
                .build();
        return authInfo;
    }
}
    @Builder
    @Value
    class AuthInfo
    {
        String email;
        String password;
        String role;
    }

