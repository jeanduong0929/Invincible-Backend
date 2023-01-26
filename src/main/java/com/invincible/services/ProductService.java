package com.invincible.services;

import com.invincible.dtos.requests.ProductRequest;
import com.invincible.entities.Category;
import com.invincible.entities.Product;
import com.invincible.repositories.CategoryRepository;
import com.invincible.repositories.ProductRepository;
import com.invincible.utils.custom_exceptions.CategoryNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class ProductService {
    private final ProductRepository prodRepo;
    private final CategoryRepository catRepo;

    public Product createProduct(ProductRequest req) {
        Optional<Category> category = catRepo.findAll()
                .stream()
                .filter(c -> c.getId().equals(req.getCategory_id()))
                .findFirst();
        if (category.isEmpty()) throw new CategoryNotFoundException("Category not found");
        Product newProduct = new Product(req, category.get());
        prodRepo.save(newProduct);
        return newProduct;
    }
}
