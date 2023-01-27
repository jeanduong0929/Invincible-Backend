package com.invincible.controllers;

import com.invincible.dtos.requests.ProductRequest;
import com.invincible.dtos.responses.Principal;
import com.invincible.entities.Product;
import com.invincible.services.ProductService;
import com.invincible.services.TokenService;
import com.invincible.utils.custom_exceptions.InvalidAuthException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/product")
public class ProductController {
  private final ProductService prodService;
  private final TokenService tokenService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Product addProduct(@RequestBody ProductRequest req, HttpServletRequest sreq) {
    String token = sreq.getHeader("Auth-Token");
    if (token == null)
      throw new InvalidAuthException("Invalid token");
    if (token.isEmpty())
      throw new InvalidAuthException("Invalid token");
    Principal principal = tokenService.extractRequesterDetails(token);
    if (principal == null)
      throw new InvalidAuthException("Invalid token");
    return prodService.createProduct(req);
  }
}
