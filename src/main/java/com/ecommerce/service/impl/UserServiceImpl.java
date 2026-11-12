package com.ecommerce.service.impl;

import com.ecommerce.dto.request.UserRequestDto;
import com.ecommerce.dto.response.UserResponseDto;
import com.ecommerce.entity.AppUser;
import com.ecommerce.enums.Role;
import com.ecommerce.exception.DuplicateEmailException;
import com.ecommerce.exception.UserNotFoundException;
import com.ecommerce.mapper.UserMapper;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto createUser(UserRequestDto dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }

        AppUser appUser = userMapper.mapToEntity(dto);
        appUser.setRole(Role.USER);
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        AppUser appUserSaved = userRepository.save(appUser);
        return userMapper.mapToDto(appUserSaved);

    }

    @Override
    public List<UserResponseDto> getAllUsers() {

        List<AppUser> appUsers = userRepository.findAll();
        List<UserResponseDto> dtos = new ArrayList<>();

        for (AppUser appUser : appUsers) {
            dtos.add(userMapper.mapToDto(appUser));
        }
        return dtos;
    }

    @Override
    public UserResponseDto getUserById(Long id) {

        AppUser appUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.mapToDto(appUser);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        AppUser appUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        appUser.setFirstName(dto.getFirstName());
        appUser.setLastName(dto.getLastName());

        if (userRepository.existsByEmail(dto.getEmail()) && ! (appUser.getEmail().equals(dto.getEmail()))) {
            throw new DuplicateEmailException("Email already exists");
        }
        appUser.setEmail(dto.getEmail());
        appUser.setPhone(dto.getPhone());
        appUser.setAddress(dto.getAddress());

        AppUser appUserUpdated = userRepository.save(appUser);
        return userMapper.mapToDto(appUserUpdated);

    }

    @Override
    public void deleteUser(Long id) {

        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.deleteById(id);

    }
}
