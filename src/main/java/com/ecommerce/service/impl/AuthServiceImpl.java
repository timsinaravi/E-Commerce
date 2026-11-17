package com.ecommerce.service.impl;

import com.ecommerce.dto.request.AuthRequestDto;
import com.ecommerce.dto.request.UserRequestDto;
import com.ecommerce.dto.response.AuthResponseDto;
import com.ecommerce.dto.response.UserResponseDto;
import com.ecommerce.security.jwt.JwtService;
import com.ecommerce.service.AuthService;
import com.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;


    @Override
    public UserResponseDto register(UserRequestDto dto) {
        return userService.createUser(dto);
    }

    @Override
    public AuthResponseDto logIn(AuthRequestDto dto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        String token = jwtService.generateToken(dto.getEmail());
        return new AuthResponseDto(token);
    }
}
