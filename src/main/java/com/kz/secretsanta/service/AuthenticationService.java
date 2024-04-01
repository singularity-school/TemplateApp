package com.kz.secretsanta.service;

import com.kz.secretsanta.dto.authentication.AuthenticationDto;
import com.kz.secretsanta.dto.authentication.LoginDto;
import com.kz.secretsanta.dto.authentication.RegisterDto;
import com.kz.secretsanta.exception.EmailAlreadyExistsException;

public interface AuthenticationService {
    AuthenticationDto register(RegisterDto registerDto) throws EmailAlreadyExistsException;
    AuthenticationDto login(LoginDto loginDto);
}
