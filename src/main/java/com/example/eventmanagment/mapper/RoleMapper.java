package com.example.eventmanagment.mapper;

import com.example.eventmanagment.dto.role.RoleDto;
import com.example.eventmanagment.entities.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role role);
    Role toEntity(RoleDto roleDto);
    List<RoleDto> toDtoList(List<Role> roles);
    List<Role> toEntityList(List<RoleDto> roleDtos);


}
