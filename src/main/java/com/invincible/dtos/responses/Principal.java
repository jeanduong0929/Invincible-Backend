package com.invincible.dtos.responses;

import com.invincible.dtos.requests.LoginRequest;
import com.invincible.entities.User;
import lombok.*;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Principal {
    private String id;
    private String username;
    private String email;
    private String role;
    private String token;

    public Principal(String id, String username, String email, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public Principal(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole().getRole();
    }
}
