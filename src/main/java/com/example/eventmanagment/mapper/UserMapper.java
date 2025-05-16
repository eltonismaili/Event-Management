package com.example.eventmanagment.mapper;

import com.example.eventmanagment.dto.user.CreateUserRequest;
import com.example.eventmanagment.dto.user.UpdateUserRequest;
import com.example.eventmanagment.dto.user.UserDto;
import com.example.eventmanagment.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);



    User toEntityCreate(CreateUserRequest request);

    User toEntityUpdate(UpdateUserRequest request);

    List<UserDto> toDtoList(List<User> users);
    List<User> toEntityList(List<UserDto> userDtos);
}
