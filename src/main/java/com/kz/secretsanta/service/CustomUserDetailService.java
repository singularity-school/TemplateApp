package com.kz.secretsanta.service;

import com.kz.secretsanta.exception.EmailAlreadyExistsException;
import com.kz.secretsanta.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailService {
    UserDetailsService userDetailsService();

    User create(User user) throws EmailAlreadyExistsException;
}
