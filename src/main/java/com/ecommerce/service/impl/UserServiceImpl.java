package com.ecommerce.service.impl;

import com.ecommerce.dto.request.UserRequestDto;
import com.ecommerce.dto.response.UserResponseDto;
import com.ecommerce.entity.User;
import com.ecommerce.enums.Role;
import com.ecommerce.exception.DuplicateEmailException;
import com.ecommerce.exception.UserNotFoundException;
import com.ecommerce.mapper.UserMapper;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto createUser(UserRequestDto dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }

        User user = userMapper.mapToEntity(dto);
        user.setRole(Role.USER);
        User userSaved = userRepository.save(user);
        return userMapper.mapToDto(userSaved);

    }

    @Override
    public List<UserResponseDto> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserResponseDto> dtos = new ArrayList<>();

        for (User user : users) {
            dtos.add(userMapper.mapToDto(user));
        }
        return dtos;
    }

    @Override
    public UserResponseDto getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.mapToDto(user);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());

        User userUpdated = userRepository.save(user);
        return userMapper.mapToDto(userUpdated);

    }

    @Override
    public void deleteUser(Long id) {

        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.deleteById(id);

    }
}
