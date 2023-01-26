package com.invincible.controllers;

import com.invincible.dtos.requests.CategoryRequest;
import com.invincible.dtos.responses.Principal;
import com.invincible.entities.Category;
import com.invincible.services.CategoryService;
import com.invincible.services.TokenService;
import com.invincible.utils.custom_exceptions.InvalidAuthException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    private final CategoryService catService;
    private final TokenService tokenService;

    @GetMapping(value = "/all")
    public List<Category> getAll() {
        return catService.getAllCategories();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody CategoryRequest req, HttpServletRequest sreq) {
        String token = sreq.getHeader("Auth-Token");
        if (token == null) throw new InvalidAuthException("Invalid token");
        if (token.isEmpty()) throw new InvalidAuthException("Invalid token");
        Principal principal = tokenService.extractRequesterDetails(token);
        if (principal == null) throw new InvalidAuthException("Invalid token");
        if (!principal.getRole().equals("ADMIN")) throw new InvalidAuthException("You are unauthorized to do this");

        String categoryReformat = catService.correctFormat(req.getCategory());
        req.setCategory(categoryReformat);
        return catService.saveCategory(req);
    }
}
