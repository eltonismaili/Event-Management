package com.example.eventmanagment.dto.role;

import com.example.eventmanagment.entities.enums.Roles;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Long id;
    @NotBlank
    @NotNull
    private Roles roleType;
}
