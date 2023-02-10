package com.invincible.services;

import com.invincible.dtos.requests.CartRequest;
import com.invincible.entities.Cart;
import com.invincible.entities.User;
import com.invincible.repositories.CartRepository;
import com.invincible.repositories.UserRepository;
import com.invincible.utils.custom_exceptions.InvalidAuthException;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CartService {
  private CartRepository cartRepo;
  private UserRepository userRepo;

  public CartService(CartRepository cartRepo, UserRepository userRepo) {
    this.cartRepo = cartRepo;
    this.userRepo = userRepo;
  }

  public Cart addCartToUser(CartRequest req) {
    Optional<User> foundUser = userRepo.findById(req.getUserId());

    if (foundUser.isEmpty()) {
      throw new InvalidAuthException("There is no user with that id");
    }

    Cart newCart = new Cart(req, foundUser.get());
    cartRepo.save(newCart);

    cartRepo.linkCartsProducts(newCart.getId(), req.getProductId());
    return newCart;
  }
}
