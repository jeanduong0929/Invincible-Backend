package com.invincible.controllers;

import com.invincible.dtos.requests.CartRequest;
import com.invincible.entities.Cart;
import com.invincible.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
  private CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Cart addToCart(@RequestBody CartRequest req) {
    Cart newCart = cartService.addCartToUser(req);
    return newCart;
  }
}
