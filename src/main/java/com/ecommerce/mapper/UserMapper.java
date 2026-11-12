package com.ecommerce.mapper;

import com.ecommerce.dto.request.UserRequestDto;
import com.ecommerce.dto.response.UserResponseDto;
import com.ecommerce.entity.AppUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public AppUser mapToEntity(UserRequestDto dto){

        AppUser appUser = new AppUser();
        appUser.setFirstName(dto.getFirstName());
        appUser.setLastName(dto.getLastName());
        appUser.setEmail(dto.getEmail());
        appUser.setPassword(dto.getPassword());
        appUser.setPhone(dto.getPhone());
        appUser.setAddress(dto.getAddress());

        return appUser;
    }

    public UserResponseDto mapToDto(AppUser appUser) {

        UserResponseDto dto = new UserResponseDto();
        dto.setId(appUser.getId());
        dto.setFirstName(appUser.getFirstName());
        dto.setLastName(appUser.getLastName());
        dto.setEmail(appUser.getEmail());
        dto.setPhone(appUser.getPhone());
        dto.setAddress(appUser.getAddress());
        dto.setRole(appUser.getRole());

        return dto;
    }
}
