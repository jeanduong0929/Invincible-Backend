package com.invincible.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "in_role")
public class Role {
    @Id
    private String id;

    @Column(name = "role", nullable = false)
    private String role;

    @OneToMany(
            mappedBy = "role",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<User> users;
}
