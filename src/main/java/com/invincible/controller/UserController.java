package com.invincible.controller;

import com.invincible.dtos.requests.RegisterRequest;
import com.invincible.dtos.responses.Principal;
import com.invincible.services.TokenService;
import com.invincible.services.UserService;
import com.invincible.utils.custom_exceptions.InvalidRegisterException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;

    @PostMapping
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
                                principal = userService.createNewUser(req);
                                String token = tokenService.generateToken(principal);
                                principal.setToken(token);
                            } else throw new InvalidRegisterException("Passwords do not match");
                        } else throw new InvalidRegisterException("Password needs to be minimum 8 characters, at least 1 letter and 1 number");
                    } else throw new InvalidRegisterException("Email is already in used");
                } else throw new InvalidRegisterException("The email provided is invalid");
            } else throw new InvalidRegisterException("Username is already taken");
        } else throw new InvalidRegisterException("Username needs to be 8-20 characters long");

        return principal;
    }

}
