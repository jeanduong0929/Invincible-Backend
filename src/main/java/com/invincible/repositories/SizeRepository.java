package com.invincible.repositories;

import com.invincible.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, String> {
  @Modifying
  @Query(
      value =
          "INSERT INTO in_products_sizes (product_id, size_id) VALUES (?1, ?2)",
      nativeQuery = true)
  void
  linkProductsSizes(String product_id, String size_id);
}
