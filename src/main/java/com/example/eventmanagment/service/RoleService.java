package com.example.eventmanagment.service;

import com.example.eventmanagment.dto.role.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> findAll();

    RoleDto findById(Long id);

    RoleDto create(RoleDto roleDto);

    RoleDto update(Long id, RoleDto roleDto);

    void delete(Long id);
}
