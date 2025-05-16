package com.example.eventmanagment.controller;

import com.example.eventmanagment.dto.role.RoleDto;
import com.example.eventmanagment.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;


    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RoleDto> create(@RequestBody RoleDto roleDto) {
        var createdRoles = roleService.create(roleDto);
        return ResponseEntity.status(201).body(createdRoles);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> modify(@PathVariable Long id, @RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleService.update(id, roleDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeById(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
