package com.ecommerce.mapper;

import com.ecommerce.dto.request.UserRequestDto;
import com.ecommerce.dto.response.UserResponseDto;
import com.ecommerce.entity.User;
import com.ecommerce.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToEntity(UserRequestDto dto){

        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());

        return user;
    }

    public UserResponseDto mapToDto(User user) {

        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setAddress(user.getAddress());
        dto.setRole(user.getRole());

        return dto;
    }
}
