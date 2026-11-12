package com.ecommerce.service;

import com.ecommerce.dto.request.AuthRequestDto;

public interface AuthService {

    String logIn(AuthRequestDto dto);
}
