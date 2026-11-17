package com.ecommerce.service;

import com.ecommerce.dto.request.AuthRequestDto;
import com.ecommerce.dto.request.UserRequestDto;
import com.ecommerce.dto.response.AuthResponseDto;
import com.ecommerce.dto.response.UserResponseDto;

public interface AuthService {

    UserResponseDto register(UserRequestDto dto);

    AuthResponseDto logIn(AuthRequestDto dto);


}
