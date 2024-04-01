package com.kz.secretsanta.service.impl;

import com.kz.secretsanta.dto.authentication.AuthenticationDto;
import com.kz.secretsanta.dto.authentication.LoginDto;
import com.kz.secretsanta.dto.authentication.RegisterDto;
import com.kz.secretsanta.exception.EmailAlreadyExistsException;
import com.kz.secretsanta.model.User;
import com.kz.secretsanta.service.AuthenticationService;
import com.kz.secretsanta.service.CustomUserDetailService;
import com.kz.secretsanta.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final CustomUserDetailService customUserDetailsService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationDto register(RegisterDto registerDto) throws EmailAlreadyExistsException {
        var user = User.builder()
                .name(registerDto.getName())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();

        customUserDetailsService.create(user);

        var token = jwtUtil.generateToken(user);

        return AuthenticationDto.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationDto login(LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword()
        ));

        var user = customUserDetailsService
                .userDetailsService()
                .loadUserByUsername(loginDto.getEmail());

        var token = jwtUtil.generateToken(user);

        return AuthenticationDto.builder()
                .token(token)
                .build();
    }
}
