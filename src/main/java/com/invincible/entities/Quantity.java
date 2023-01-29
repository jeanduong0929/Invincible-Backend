package com.invincible.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.invincible.dtos.requests.QuantityRequest;
import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;
import lombok.ToString;

@ToString
@Entity
@Table(name = "in_quantity")
public class Quantity {
  @Id private String id;

  @Column(name = "quantity", nullable = false) private int quantity;

  // junction table: quantities -> sizes
  @ManyToMany(mappedBy = "quantities", fetch = FetchType.LAZY,
              cascade = CascadeType.ALL)
  @JsonBackReference
  private Set<Size> sizes;

  public Quantity() {}

  public Quantity(QuantityRequest req) {
    this.id = UUID.randomUUID().toString();
    this.quantity = req.getQuantity();
  }

  public Quantity(String id, int quantity, Set<Size> sizes) {
    this.id = id;
    this.quantity = quantity;
    this.sizes = sizes;
  }

  public String getId() { return id; }

  public void setId(String id) { this.id = id; }

  public int getQuantity() { return quantity; }

  public void setQuantity(int quantity) { this.quantity = quantity; }

  public Set<Size> getSizes() { return sizes; }

  public void setSizes(Set<Size> sizes) { this.sizes = sizes; }
}
