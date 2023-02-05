package com.invincible.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Entity
@Table(name = "in_cart")
public class Cart {
  @Id private String id;

  @Column(name = "quantity", nullable = false) private int quantity;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  @JsonBackReference
  private User user;

  @ManyToMany
  @JoinTable(name = "in_carts_products",
             joinColumns =
             {
               @JoinColumn(name = "cart_id", referencedColumnName = "id",
                           nullable = false)
             },
             inverseJoinColumns =
             {
               @JoinColumn(name = "product_id", referencedColumnName = "id",
                           nullable = false)
             })
  @JsonManagedReference
  private Set<Product> products;

  public Cart() {}

  public String getId() { return id; }

  public void setId(String id) { this.id = id; }

  public int getQuantity() { return quantity; }

  public void setQuantity(int quantity) { this.quantity = quantity; }

  public User getUser() { return user; }

  public void setUser(User user) { this.user = user; }

  public Set<Product> getProducts() { return products; }

  public void setProducts(Set<Product> products) { this.products = products; }
}
