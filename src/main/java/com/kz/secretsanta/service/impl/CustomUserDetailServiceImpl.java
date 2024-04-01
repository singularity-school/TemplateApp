package com.kz.secretsanta.service.impl;

import com.kz.secretsanta.db.UserDb;
import com.kz.secretsanta.exception.EmailAlreadyExistsException;
import com.kz.secretsanta.model.User;
import com.kz.secretsanta.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailServiceImpl implements CustomUserDetailService {

    private final UserDb db;

    @Override
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    @Override
    public User create(User user) throws EmailAlreadyExistsException {
        var opt = db.findUserByEmail(user.getEmail());
        if (opt.isPresent()) {
            throw new EmailAlreadyExistsException("Пользователь с таким email уже существует");
        }
        return db.save(user);
    }

    private User getByUsername(String username) {
        return db.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
}
