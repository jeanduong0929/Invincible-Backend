package com.invincible.controllers;

import com.invincible.dtos.requests.RegisterRequest;
import com.invincible.dtos.responses.Principal;
import com.invincible.dtos.responses.UserResponse;
import com.invincible.entities.Role;
import com.invincible.entities.User;
import com.invincible.services.RoleService;
import com.invincible.services.TokenService;
import com.invincible.services.UserService;
import com.invincible.utils.custom_exceptions.InvalidAuthException;
import com.invincible.utils.custom_exceptions.InvalidRegisterException;
import com.invincible.utils.custom_exceptions.RoleNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final RoleService roleService;
    private final UserService userService;
    private final TokenService tokenService;

    @GetMapping
    public List<UserResponse> getAll(HttpServletRequest req) {
        String token = req.getHeader("Auth-Token");
        if (token == null) throw new InvalidAuthException("Invalid token");
        if (token.isEmpty()) throw new InvalidAuthException("Invalid token");
        Principal principal = tokenService.extractRequesterDetails(token);
        if (principal == null) throw new InvalidAuthException("Invalid token");
        if (!principal.getRole().equals("ADMIN")) throw new InvalidAuthException("You are unauthorized to do this");
        return userService.getAllUsers();
    }
}
