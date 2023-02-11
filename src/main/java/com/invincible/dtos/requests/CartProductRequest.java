package com.invincible.dtos.requests;

import lombok.ToString;

/**
 * CartProduct
 */
@ToString
public class CartProductRequest {
  private String cartId;
  private String productId;

  public CartProductRequest() {}

  public CartProductRequest(String cartId, String productId) {
    this.cartId = cartId;
    this.productId = productId;
  }

  public String getCartId() { return cartId; }

  public void setCartId(String cartId) { this.cartId = cartId; }

  public String getProductId() { return productId; }

  public void setProductId(String productId) { this.productId = productId; }
}
