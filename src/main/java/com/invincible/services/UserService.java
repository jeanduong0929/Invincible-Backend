package com.invincible.services;

import com.invincible.dtos.requests.LoginRequest;
import com.invincible.dtos.responses.Principal;
import com.invincible.dtos.responses.UserResponse;
import com.invincible.entities.User;
import com.invincible.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class UserService {
    private final SecurityService securityService;
    private final UserRepository userRepo;

    public Principal createNewUser(User newUser) {
        userRepo.save(newUser);
        return new Principal(newUser);
    }

    public Optional<Principal> validateLogin(LoginRequest req) {
        return userRepo.findAll()
                .stream()
                .filter(u -> {
                    byte[] salt = u.getSalt();
                    byte[] hashedPassword = u.getPassword();
                    try {
                        return Arrays.equals(hashedPassword, securityService.hashingMethod(req.getPassword(), salt));
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    return false;
                }).map(Principal::new)
                .findFirst();
    }

    /* ------------------------------ LISTS ------------------------------ */

    public List<UserResponse> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    /* ------------------------------ BOOLEANS ------------------------------ */

    // username must be 8-20 characters
    public boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    public boolean isUniqueUsername(String username) {
        return userRepo.findAll()
                .stream()
                .noneMatch(u -> u.getUsername().equals(username));
    }

    public boolean isValidEmail(String email) {
        return email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

    public boolean isUniqueEmail(String email) {
        return userRepo.findAll()
                .stream()
                .noneMatch(u -> u.getEmail().equals(email));
    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    public boolean isSamePassword(String pwd1, String pwd2) {
        return pwd1.equals(pwd2);
    }
}
