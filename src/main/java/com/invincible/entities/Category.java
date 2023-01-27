package com.invincible.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.invincible.dtos.requests.CategoryRequest;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;
import lombok.ToString;

@ToString
@Entity
@Table(name = "in_category")
public class Category {
  // FIELDS
  @Id private String id;

  @Column(name = "category", nullable = false) private String category;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
             mappedBy = "category")
  @JsonManagedReference
  private List<Product> productList;

  // CONSTRUCTORS
  public Category() {}

  public Category(CategoryRequest req) {
    this.id = UUID.randomUUID().toString();
    this.category = req.getCategory();
  }

  public Category(String id, String category, List<Product> productList) {
    this.id = id;
    this.category = category;
    this.productList = productList;
  }

  // GETTERS/SETTERS
  public String getId() { return id; }

  public void setId(String id) { this.id = id; }

  public String getCategory() { return category; }

  public void setCategory(String category) { this.category = category; }

  public List<Product> getProductList() { return productList; }

  public void setProductList(List<Product> productList) {
    this.productList = productList;
  }
}
