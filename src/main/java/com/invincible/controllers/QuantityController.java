package com.invincible.controllers;

import com.invincible.dtos.requests.QuantityRequest;
import com.invincible.dtos.responses.Principal;
import com.invincible.entities.Quantity;
import com.invincible.services.QuantityService;
import com.invincible.services.TokenService;
import com.invincible.utils.custom_exceptions.InvalidAuthException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/quantity")
public class QuantityController {
  private final QuantityService quantService;
  private final TokenService tokenService;

  public QuantityController(QuantityService quantService,
                            TokenService tokenService) {
    this.quantService = quantService;
    this.tokenService = tokenService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Quantity addQuantity(@RequestBody QuantityRequest req,
                              HttpServletRequest sreq) {
    String token = sreq.getHeader("Auth-Token");
    if (token == null)
      throw new InvalidAuthException("Invalid token");
    if (token.isEmpty())
      throw new InvalidAuthException("Invalid token");
    Principal principal = tokenService.extractRequesterDetails(token);
    if (principal == null)
      throw new InvalidAuthException("Invalid token");

    return quantService.addQuantity(req);
  }
}
