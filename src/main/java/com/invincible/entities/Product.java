package com.invincible.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.invincible.dtos.requests.ProductRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "in_product")
public class Product {
    @Id
    private String id;

    @Column(name = "product", nullable = false)
    private String product;

    @Column(name = "price", nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(
            name = "category_id",
            nullable = false

    )
    @JsonBackReference
    private Category category;

    // junction table: product -> sizes
    @ManyToMany
    @JoinTable(
            name = "in_products_sizes",
            joinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "size_id", referencedColumnName = "id", nullable = false)
            }
    )
    @JsonManagedReference
    private Set<Size> sizes;

    public Product(ProductRequest req, Category category) {
        this.id = UUID.randomUUID().toString();
        this.product = req.getProduct();
        this.price = req.getPrice();
        this.category = category;
    }
}
