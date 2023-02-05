package com.invincible.dtos.requests;

import lombok.ToString;

@ToString
public class SizeRequest {
  private String size;
  private String productId;

  public SizeRequest() {}

  public SizeRequest(String size, String productId) {
    this.size = size;
    this.productId = productId;
  }

  public String getSize() { return size; }

  public void setSize(String size) { this.size = size; }

  public String getProductId() { return productId; }

  public void setProductId(String productId) { this.productId = productId; }
}
