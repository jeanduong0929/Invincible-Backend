package com.invincible.controllers;

import com.invincible.dtos.requests.LoginRequest;
import com.invincible.dtos.requests.RegisterRequest;
import com.invincible.dtos.responses.Principal;
import com.invincible.entities.Role;
import com.invincible.entities.User;
import com.invincible.services.RoleService;
import com.invincible.services.SecurityService;
import com.invincible.services.TokenService;
import com.invincible.services.UserService;
import com.invincible.utils.custom_exceptions.InvalidAuthException;
import com.invincible.utils.custom_exceptions.InvalidRegisterException;
import com.invincible.utils.custom_exceptions.RoleNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private final TokenService tokenService;
    private final UserService userService;
    private final SecurityService securityService;
    private final RoleService roleService;

    @PostMapping("/login")
    @ExceptionHandler(InvalidAuthException.class)
    public Principal login(@RequestBody LoginRequest req) {
        Optional<Principal> principal = userService.validateLogin(req);

        // if login fail
        if (principal.isEmpty()) throw new InvalidAuthException("Incorrect username or password");

        // generate jwt token
        String token = tokenService.generateToken(principal.get());

        // set token in principal class
        principal.get().setToken(token);

        return principal.get();
    }

    @PostMapping("/register")
    @ExceptionHandler(InvalidRegisterException.class)
    @ResponseStatus(HttpStatus.CREATED)
    public Principal register(@RequestBody RegisterRequest req) {
        String username = req.getUsername();
        String email = req.getEmail();
        String pwd1 = req.getPassword1();
        String pwd2 = req.getPassword2();
        Principal principal;

        if (userService.isValidUsername(username)) {
            if (userService.isUniqueUsername(username)) {
                if (userService.isValidEmail(email)) {
                    if (userService.isUniqueEmail(email)) {
                        if (userService.isValidPassword(pwd1)) {
                            if (userService.isSamePassword(pwd1, pwd2)) {
                                // if role exists
                                Optional<Role> role = roleService.getDefaultRole();

                                // role does not exist
                                if (role.isEmpty())
                                    throw new RoleNotFoundException("Creating new user failed because DEFAULT role is not established");

                                User newUser = new User(req, role.get());

                                try { // encrypt user's password
                                    byte[] salt = securityService.generateSalt();
                                    byte[] hashedPassword = securityService.hashingMethod(req.getPassword1(), salt);
                                    newUser.setSalt(salt);
                                    newUser.setPassword(hashedPassword);
                                } catch (NoSuchAlgorithmException e) {
                                    e.printStackTrace();
                                }

                                // get principal from created user
                                principal = userService.createNewUser(newUser);

                                // generate token
                                String token = tokenService.generateToken(principal);
                                principal.setToken(token);
                            } else throw new InvalidRegisterException("Passwords do not match");
                        } else
                            throw new InvalidRegisterException("Password needs to be minimum 8 characters, at least 1 letter and 1 number");
                    } else throw new InvalidRegisterException("Email is already in used");
                } else throw new InvalidRegisterException("The email provided is invalid");
            } else throw new InvalidRegisterException("Username is already taken");
        } else throw new InvalidRegisterException("Username needs to be 8-20 characters long");

        return principal;
    }
}
