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
@Table(name = "in_size")
public class Size {
    @Id
    private String id;

    @Column(name = "size", nullable = false)
    private String size;

    // junction table: sizes -> products
    @ManyToMany(
            mappedBy = "sizes",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JsonBackReference
    private Set<Product> products;

    // junction table: sizes -> quantities
    @ManyToMany
    @JoinTable(
            name = "in_sizes_quantities",
            joinColumns = {
                    @JoinColumn(name = "size_id", referencedColumnName = "id", nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "quantity_id", referencedColumnName = "id", nullable = false)
            }
    )
    @JsonManagedReference
    private Set<Quantity> quantities;
}
