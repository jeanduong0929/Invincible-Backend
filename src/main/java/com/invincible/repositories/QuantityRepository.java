package com.invincible.repositories;

import com.invincible.entities.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuantityRepository extends JpaRepository<Quantity, String> {
  @Modifying
  @Query(
      value =
          "INSERT INTO in_sizes_quantities (size_id, quantity_id) VALUES (?1, ?2)",
      nativeQuery = true)
  void
  linkSizesQuantity(String size_id, String quantity_id);
}
