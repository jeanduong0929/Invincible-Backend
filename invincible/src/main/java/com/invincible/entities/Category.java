package com.invincible.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "in_category")
public class Category {
    @Id
    private String id;

    @Column(name = "category", nullable = false)
    private String category;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "category"
    )
    @JsonManagedReference
    private List<Product> productList;
}
