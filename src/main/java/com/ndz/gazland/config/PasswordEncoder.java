package com.ndz.gazland.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    public String encoder(String password)
    {
        return new BCryptPasswordEncoder().encode(password);
    }
}
