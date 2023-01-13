package com.invincible.dtos.requests;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.invincible.entities.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryRequest {
    private String category;
}
