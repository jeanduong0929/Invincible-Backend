package com.invincible.dtos.requests;

import lombok.ToString;

@ToString
public class QuantityRequest {
  private int quantity;
  private String size_id;

  public QuantityRequest() {}

  public QuantityRequest(int quantity, String size_id) {
    this.quantity = quantity;
    this.size_id = size_id;
  }

  public int getQuantity() { return quantity; }

  public void setQuantity(int quantity) { this.quantity = quantity; }

  public String getSize_id() { return size_id; }

  public void setSize_id(String size_id) { this.size_id = size_id; }
}
