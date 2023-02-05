package com.invincible.repositories;

import com.invincible.entities.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
  List<Product> findByCategoryId(String categoryId);
}
