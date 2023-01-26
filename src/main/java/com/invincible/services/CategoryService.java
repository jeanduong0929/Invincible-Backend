package com.invincible.services;

import com.invincible.dtos.requests.CategoryRequest;
import com.invincible.entities.Category;
import com.invincible.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository catRepo;

    public Category saveCategory(CategoryRequest req) {
        Category newCategory = new Category(req);
        catRepo.save(newCategory);
        return newCategory;
    }
    public List<Category> getAllCategories() {
        return catRepo.findAll();
    }

    public String correctFormat(String category) {
        String ss1 = String.valueOf(category.charAt(0));
        String ss2 = category.substring(1);
        return ss1.toUpperCase() + ss2.toLowerCase();
    }
}
