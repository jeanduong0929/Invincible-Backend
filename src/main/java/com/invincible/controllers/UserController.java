package com.invincible.controllers;

import com.invincible.dtos.responses.Principal;
import com.invincible.dtos.responses.UserResponse;
import com.invincible.services.TokenService;
import com.invincible.services.UserService;
import com.invincible.utils.custom_exceptions.InvalidAuthException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
  private final UserService userService;
  private final TokenService tokenService;

  public UserController(UserService userService, TokenService tokenService) {
    this.userService = userService;
    this.tokenService = tokenService;
  }

  @GetMapping
  public List<UserResponse> getAll(HttpServletRequest req) {
    String token = req.getHeader("Auth-Token");
    if (token == null)
      throw new InvalidAuthException("Invalid token");

    if (token.isEmpty())
      throw new InvalidAuthException("Invalid token");

    Principal principal = tokenService.extractRequesterDetails(token);

    if (principal == null)
      throw new InvalidAuthException("Invalid token");

    if (!principal.getRole().equals("ADMIN"))
      throw new InvalidAuthException("You are unauthorized to do this");

    return userService.getAllUsers();
  }
}
