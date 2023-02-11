package com.invincible.controllers;

import com.invincible.dtos.requests.CartProductRequest;
import com.invincible.dtos.requests.CartUserRequest;
import com.invincible.entities.Cart;
import com.invincible.services.CartService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
  private CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping("/id")
  public Cart getCartByid(@RequestParam String id) {
    return cartService.findCartById(id);
  }

  @PostMapping("/user")
  @ResponseStatus(HttpStatus.CREATED)
  public Cart addToCart(@RequestBody CartUserRequest req) {
    return cartService.addCartToUser(req);
  }

  @PostMapping("/product")
  @ResponseStatus(HttpStatus.CREATED)
  public Cart addProductToCart(@RequestBody CartProductRequest req) {
    return cartService.addProductToCart(req);
  }

  @GetMapping
  public List<Cart> findAllCart() {
    return cartService.findAllCart();
  }
}
