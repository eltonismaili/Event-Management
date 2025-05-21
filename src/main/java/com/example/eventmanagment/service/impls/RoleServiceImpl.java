package com.example.eventmanagment.service.impls;

import com.example.eventmanagment.dto.role.RoleDto;
import com.example.eventmanagment.exceptions.role.RoleNotFoundException;
import com.example.eventmanagment.mapper.RoleMapper;
import com.example.eventmanagment.repository.RoleRepository;
import com.example.eventmanagment.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;
    private final RoleMapper mapper;
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    @Override
    public List<RoleDto> findAll() {
        var roles = repository.findAll();
        return mapper.toDtoList(roles);
    }

    @Override
    public RoleDto findById(Long id) {
        var role = repository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
        return mapper.toDto(role);
    }

    @Override
    public RoleDto create(RoleDto roleDto) {
        var roles = mapper.toEntity(roleDto);
        var savedRoles = repository.save(roles);
        return mapper.toDto(savedRoles);
    }

    @Override
    public RoleDto update(Long id, RoleDto roleDto) {
        if (!roleRepository.existsById(id)) {
            throw new RoleNotFoundException(id);
        }
        var roles = roleMapper.toEntity(roleDto);
        var updatedRoles = repository.save(roles);
        return roleMapper.toDto(updatedRoles);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RoleNotFoundException(id);
        }
        repository.deleteById(id);

    }
}
