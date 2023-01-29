package com.invincible.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.invincible.dtos.requests.ProductRequest;
import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Entity
@Table(name = "in_product")
public class Product {
  // FIELDS
  @Id private String id;

  @Column(name = "product", nullable = false) private String product;

  @Column(name = "price", nullable = false) private int price;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false

              )
  @JsonBackReference
  private Category category;

  // junction table: product -> sizes
  @ManyToMany
  @JoinTable(name = "in_products_sizes",
             joinColumns =
             {
               @JoinColumn(name = "product_id", referencedColumnName = "id",
                           nullable = false)
             },
             inverseJoinColumns =
             {
               @JoinColumn(name = "size_id", referencedColumnName = "id",
                           nullable = false)
             })
  @JsonManagedReference
  private Set<Size> sizes;

  // CONSTRUCTORS
  public Product() {}

  public Product(ProductRequest req, Category category) {
    this.id = UUID.randomUUID().toString();
    this.product = req.getProduct();
    this.price = req.getPrice();
    this.category = category;
  }

  // GETTERS/SETTERS
  public String getId() { return id; }

  public void setId(String id) { this.id = id; }

  public String getProduct() { return product; }

  public void setProduct(String product) { this.product = product; }

  public int getPrice() { return price; }

  public void setPrice(int price) { this.price = price; }

  public Category getCategory() { return category; }

  public void setCategory(Category category) { this.category = category; }

  public Set<Size> getSizes() { return sizes; }

  public void setSizes(Set<Size> sizes) { this.sizes = sizes; }
}
