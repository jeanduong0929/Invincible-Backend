package com.invincible.controller;

import com.invincible.dtos.requests.LoginRequest;
import com.invincible.dtos.responses.Principal;
import com.invincible.services.TokenService;
import com.invincible.services.UserService;
import com.invincible.utils.custom_exceptions.AuthException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private final TokenService tokenService;
    private final UserService userService;

    @PostMapping
    @ExceptionHandler(AuthException.class)
    public Principal login(@RequestBody LoginRequest req) {
        Optional<Principal> principal = userService.validateLogin(req);

        // if login fail
        if (principal.isEmpty()) throw new AuthException("Incorrect username or password");

        // generate jwt token
        String token = tokenService.generateToken(principal.get());

        // set token in principal class
        principal.get().setToken(token);
        
        return principal.get();
    }
}
