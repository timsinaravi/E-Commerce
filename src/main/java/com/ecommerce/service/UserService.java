package com.ecommerce.service;

import com.ecommerce.dto.request.UserRequestDto;
import com.ecommerce.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto dto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Long id);

    UserResponseDto updateUser(Long id, UserRequestDto dto);

    void deleteUser(Long id);

}
