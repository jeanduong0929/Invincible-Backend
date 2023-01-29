package com.invincible.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.invincible.dtos.requests.SizeRequest;
import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;
import lombok.ToString;

@ToString
@Entity
@Table(name = "in_size")
public class Size {
  @Id private String id;

  @Column(name = "size", nullable = false) private String size;

  // junction table: sizes -> products
  @ManyToMany(mappedBy = "sizes", fetch = FetchType.LAZY,
              cascade = CascadeType.ALL)
  @JsonBackReference
  private Set<Product> products;

  // junction table: sizes -> quantities
  @ManyToMany
  @JoinTable(name = "in_sizes_quantities",
             joinColumns =
             {
               @JoinColumn(name = "size_id", referencedColumnName = "id",
                           nullable = false)
             },
             inverseJoinColumns =
             {
               @JoinColumn(name = "quantity_id", referencedColumnName = "id",
                           nullable = false)
             })
  @JsonManagedReference
  private Set<Quantity> quantities;

  public Size() {}

  public Size(SizeRequest req) {
    this.id = UUID.randomUUID().toString();
    this.size = req.getSize();
  }

  public Size(String id, String size, Set<Product> products,
              Set<Quantity> quantities) {
    this.id = id;
    this.size = size;
    this.products = products;
    this.quantities = quantities;
  }

  public String getId() { return id; }

  public void setId(String id) { this.id = id; }

  public String getSize() { return size; }

  public void setSize(String size) { this.size = size; }

  public Set<Product> getProducts() { return products; }

  public void setProducts(Set<Product> products) { this.products = products; }

  public Set<Quantity> getQuantities() { return quantities; }

  public void setQuantities(Set<Quantity> quantities) {
    this.quantities = quantities;
  }
}
