package com.invincible.dtos.requests;

import java.math.BigDecimal;
import lombok.ToString;

@ToString
public class ProductRequest {
  // FIELDS
  private String product;
  private BigDecimal price;
  private String categoryId;

  // CONSTRUCTORS
  public ProductRequest() {}

  public ProductRequest(String product, BigDecimal price, String categoryId) {
    this.product = product;
    this.price = price;
    this.categoryId = categoryId;
  }

  public String getProduct() { return product; }

  public void setProduct(String product) { this.product = product; }

  public BigDecimal getPrice() { return price; }

  public void setPrice(BigDecimal price) { this.price = price; }

  public String getCategoryId() { return categoryId; }

  public void setCategoryId(String categoryId) { this.categoryId = categoryId; }
}
