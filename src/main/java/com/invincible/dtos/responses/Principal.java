package com.invincible.dtos.responses;

import lombok.*;

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
}
