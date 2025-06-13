package com.example.eventmanagment.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.eventmanagment.entities.enums.Permission.*;

@RequiredArgsConstructor
public enum Role {
    USER(Set.of(USER_READ, USER_WRITE)),
    ADMIN(Set.of(ADMIN_READ, ADMIN_WRITE, USER_READ, USER_WRITE));

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authority = new ArrayList<>(
                getPermissions().stream()
                        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                        .toList());

        authority.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authority;
    }
}
