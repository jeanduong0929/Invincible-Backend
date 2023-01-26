package com.invincible.dtos.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegisterRequest {
    private String username;
    private String email;
    private String password1;
    private String password2;
}
