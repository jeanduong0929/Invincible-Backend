package com.invincible.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "in_quantity")
public class Quantity {
    @Id
    private String id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    // junction table: quantities -> sizes
    @ManyToMany(
            mappedBy = "quantities",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JsonBackReference
    private Set<Size> sizes;
}
