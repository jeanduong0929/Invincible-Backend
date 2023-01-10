package com.invincible.controller;

import com.invincible.dtos.requests.LoginRequest;
import com.invincible.dtos.responses.Principal;
import com.invincible.services.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private final TokenService tokenService;
}
