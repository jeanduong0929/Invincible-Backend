package com.invincible.dtos.responses;

import com.invincible.entities.Cart;
import com.invincible.entities.User;
import lombok.ToString;

@ToString
public class UserResponse {
  // FIELDS
  private String id;
  private String username;
  private String email;
  private String role;
  private Cart cart;

  // CONSTRUCTORS
  public UserResponse() {}

  public UserResponse(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.email = user.getEmail();
    this.role = user.getRole().getRole();
    this.cart = user.getCart();
  }

  // GETTERS/SETTERS
  public String getId() { return id; }

  public void setId(String id) { this.id = id; }

  public String getUsername() { return username; }

  public void setUsername(String username) { this.username = username; }

  public String getEmail() { return email; }

  public void setEmail(String email) { this.email = email; }

  public String getRole() { return role; }

  public void setRole(String role) { this.role = role; }

  public Cart getCart() { return cart; }

  public void setCart(Cart cart) { this.cart = cart; }
}
