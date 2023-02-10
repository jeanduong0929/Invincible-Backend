package com.invincible.repositories;

import com.invincible.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
  @Modifying
  @Query(
      value =
          "INSERT INTO in_carts_products (cart_id, product_id) VALUES (?1, ?2)",
      nativeQuery = true)
  void
  linkCartsProducts(String cartId, String productId);
}
