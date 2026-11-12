package com.ecommerce.controller;

import com.ecommerce.dto.request.AuthRequestDto;
import com.ecommerce.dto.response.AuthResponseDto;
import com.ecommerce.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponseDto logIn(@Valid @RequestBody AuthRequestDto dto) {
        return authService.logIn(dto);
    }


}
