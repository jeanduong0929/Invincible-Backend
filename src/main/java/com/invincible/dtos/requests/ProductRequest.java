package com.invincible.dtos.requests;

import lombok.ToString;

@ToString
public class ProductRequest {
  // FIELDS
  private String product;
  private int price;
  private String category_id;

  // CONSTRUCTORS
  public ProductRequest() {}

  public ProductRequest(String product, int price, String category_id) {
    this.product = product;
    this.price = price;
    this.category_id = category_id;
  }

  // GETTERS/SETTERS
  public String getProduct() { return product; }

  public void setProduct(String product) { this.product = product; }

  public int getPrice() { return price; }

  public void setPrice(int price) { this.price = price; }

  public String getCategory_id() { return category_id; }

  public void setCategory_id(String category_id) {
    this.category_id = category_id;
  }
}
