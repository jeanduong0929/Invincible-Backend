package com.invincible.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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
    private Category category;

    // junction table: product -> sizes
    @ManyToMany
    @JoinTable(
            joinColumns = {
                    @JoinColumn(name = "product_id", nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "size_id", nullable = false)
            }
    )
    @JsonBackReference
    private Set<Size> sizes;
}
