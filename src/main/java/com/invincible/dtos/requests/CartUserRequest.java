package com.invincible.dtos.requests;

import lombok.ToString;

@ToString
public class CartUserRequest {
  private String userId;
  private int quantity;

  public CartUserRequest() {}

  public CartUserRequest(String userId, int quantity) {
    this.userId = userId;
    this.quantity = quantity;
  }

  public String getUserId() { return userId; }

  public void setUserId(String userId) { this.userId = userId; }

  public int getQuantity() { return quantity; }

  public void setQuantity(int quantity) { this.quantity = quantity; }
}
