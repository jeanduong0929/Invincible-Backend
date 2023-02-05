package com.invincible.controllers;

import com.invincible.dtos.requests.ProductRequest;
import com.invincible.dtos.responses.Principal;
import com.invincible.entities.Product;
import com.invincible.services.ProductService;
import com.invincible.services.TokenService;
import com.invincible.utils.custom_exceptions.CategoryNotFoundException;
import com.invincible.utils.custom_exceptions.InvalidAuthException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/product")
public class ProductController {
  // FIELDS
  private final ProductService prodService;
  private final TokenService tokenService;

  // CONSTRUCTORS
  public ProductController(ProductService productService,
                           TokenService tokenService) {
    this.prodService = productService;
    this.tokenService = tokenService;
  }

  @GetMapping
  public List<Product> getAll() {
    return prodService.getAllProducts();
  }

  @GetMapping("/category")
  public List<Product> getProductsByCategory(@RequestParam String category) {
    return prodService.getProductByCategory(category);
  }

  @GetMapping("/id")
  public Product getProductById(@RequestParam String id) {
    return prodService.getProductById(id);
  }

  // HANDLERS
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Product addProduct(@RequestBody ProductRequest req,
                            HttpServletRequest sreq) {
    String token = sreq.getHeader("Auth-Token");
    if (token == null)
      throw new InvalidAuthException("Invalid token");
    if (token.isEmpty())
      throw new InvalidAuthException("Invalid token");
    Principal principal = tokenService.extractRequesterDetails(token);
    if (principal == null)
      throw new InvalidAuthException("Invalid token");
    return prodService.createProduct(req);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = {CategoryNotFoundException.class})
  public CategoryNotFoundException
  handledCategoryNotFoundException(CategoryNotFoundException e) {
    return e;
  }
}
