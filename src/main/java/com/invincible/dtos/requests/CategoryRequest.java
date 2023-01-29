package com.invincible.dtos.requests;

import lombok.ToString;

@ToString
public class CategoryRequest {
  // FIELDS
  private String category;

  // CONSTRUCTORS
  public CategoryRequest() {}

  public CategoryRequest(String category) { this.category = category; }

  // GETTERS/SETTERS
  public String getCategory() { return category; }

  public void setCategory(String category) { this.category = category; }
}
