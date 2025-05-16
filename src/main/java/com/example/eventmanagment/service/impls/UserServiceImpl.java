package com.example.eventmanagment.service.impls;

import com.example.eventmanagment.dto.user.CreateUserRequest;
import com.example.eventmanagment.dto.user.UpdateUserRequest;
import com.example.eventmanagment.dto.user.UserDto;
import com.example.eventmanagment.entities.Address;
import com.example.eventmanagment.entities.Role;
import com.example.eventmanagment.mapper.UserMapper;
import com.example.eventmanagment.repository.AddressRepository;
import com.example.eventmanagment.repository.RoleRepository;
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
    private final RoleRepository roleRepository;
    private final AddressRepository addressRepository;
    @Override
    public List<UserDto> findAll() {
        var users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    @Override
    public UserDto findById(Long id) {
       var user = userRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto create(CreateUserRequest request) {
        var user = userMapper.toEntityCreate(request);

        Role role = roleRepository.findById(request.getRoleId().getId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoleId(role);

        Address address = addressRepository.findById(request.getAddressId().getId())
                .orElseThrow(() -> new RuntimeException("Address not found"));
        user.setAddressId(address);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto update(Long id, UpdateUserRequest request) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        var user = userMapper.toEntityUpdate(request);
        var updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void delete(Long id) {
       var userToDelete = userRepository.findById(id);
       if (userToDelete != null) {
           userRepository.deleteById(id);
       }
    }
}
