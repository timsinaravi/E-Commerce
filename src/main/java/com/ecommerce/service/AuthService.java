package com.ecommerce.service;

import com.ecommerce.dto.request.AuthRequestDto;
import com.ecommerce.dto.response.AuthResponseDto;

public interface AuthService {

    AuthResponseDto logIn(AuthRequestDto dto);
}
