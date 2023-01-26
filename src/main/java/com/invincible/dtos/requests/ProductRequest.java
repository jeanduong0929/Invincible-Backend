package com.invincible.dtos.requests;

import com.invincible.entities.Category;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductRequest {
    private String product;
    private int price;
    private String category_id;
}
