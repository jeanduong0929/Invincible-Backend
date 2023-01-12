package com.invincible.services;

import com.invincible.entities.Role;
import com.invincible.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepo;

    public Optional<Role> getDefaultRole() {
        return roleRepo.findAll()
                .stream()
                .filter(r -> r.getRole().equals("DEFAULT"))
                .findFirst();
    }
}
