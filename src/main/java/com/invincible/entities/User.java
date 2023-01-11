package com.invincible.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.invincible.dtos.requests.RegisterRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "in_user")
public class User {
    @Id
    private String id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @JsonBackReference
    private Role role;

    public User(RegisterRequest req, Role role) {
        this.id = UUID.randomUUID().toString();
        this.username = req.getUsername();
        this.email = req.getEmail();
        this.password = req.getPassword1();
        this.role = role;
    }
}
