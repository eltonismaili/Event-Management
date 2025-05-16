package com.example.eventmanagment.service;

import com.example.eventmanagment.dto.user.CreateUserRequest;
import com.example.eventmanagment.dto.user.UpdateUserRequest;
import com.example.eventmanagment.dto.user.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto findById(Long id);
    UserDto create(CreateUserRequest request);
    UserDto update(Long id, UpdateUserRequest request);
    void delete(Long id);

}
