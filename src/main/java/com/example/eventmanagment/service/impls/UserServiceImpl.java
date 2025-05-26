package com.example.eventmanagment.service.impls;

import com.example.eventmanagment.dto.user.CreateUserRequest;
import com.example.eventmanagment.dto.user.UpdateUserRequest;
import com.example.eventmanagment.dto.user.UserDto;
import com.example.eventmanagment.entities.Address;
import com.example.eventmanagment.exceptions.address.AddressNotFoundException;
import com.example.eventmanagment.exceptions.user.UserNotFoundException;
import com.example.eventmanagment.mapper.UserMapper;
import com.example.eventmanagment.repository.AddressRepository;
import com.example.eventmanagment.repository.UserRepository;
import com.example.eventmanagment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressRepository addressRepository;

    @Override
    public List<UserDto> findAll() {
        var users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    @Override
    public UserDto findById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto create(CreateUserRequest request) {
        var user = userMapper.toEntityCreate(request);


        Address address = addressRepository.findById(request.getAddressId().getId())
                .orElseThrow(() -> new AddressNotFoundException(request.getAddressId().getId()));
        user.setAddressId(address);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto update(Long id, UpdateUserRequest request) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        var user = userMapper.toEntityUpdate(request);
        var updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}
