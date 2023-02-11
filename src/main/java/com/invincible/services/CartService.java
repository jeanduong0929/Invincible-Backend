package com.invincible.services;

import com.invincible.dtos.requests.CartProductRequest;
import com.invincible.dtos.requests.CartUserRequest;
import com.invincible.entities.Cart;
import com.invincible.entities.Product;
import com.invincible.entities.User;
import com.invincible.repositories.CartRepository;
import com.invincible.repositories.ProductRepository;
import com.invincible.repositories.UserRepository;
import com.invincible.utils.custom_exceptions.CartNotFoundException;
import com.invincible.utils.custom_exceptions.InvalidAuthException;
import com.invincible.utils.custom_exceptions.ProductNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CartService {
  private CartRepository cartRepo;
  private UserRepository userRepo;
  private ProductRepository prodRepo;

  public CartService(CartRepository cartRepo, UserRepository userRepo,
                     ProductRepository prodRepo) {
    this.cartRepo = cartRepo;
    this.userRepo = userRepo;
    this.prodRepo = prodRepo;
  }

  public Cart addCartToUser(CartUserRequest req) {
    Optional<User> foundUser = userRepo.findById(req.getUserId());

    if (foundUser.isEmpty()) {
      throw new InvalidAuthException("There is no user with that id");
    }

    Cart newCart = new Cart(req, foundUser.get());
    cartRepo.save(newCart);
    return newCart;
  }

  public Cart addProductToCart(CartProductRequest req) {
    Optional<Cart> foundCart = cartRepo.findById(req.getCartId());
    if (!foundCart.isPresent())
      throw new CartNotFoundException("There is not cart with that id");

    Optional<Product> foundProduct = prodRepo.findById(req.getProductId());
    if (!foundProduct.isPresent())
      throw new ProductNotFoundException("there is not a product with that id");

    cartRepo.linkCartsProducts(foundCart.get().getId(),
                               foundProduct.get().getId());
    return foundCart.get();
  }

  public List<Cart> findAllCart() { return cartRepo.findAll(); }

  public Cart findCartById(String id) {
    Optional<Cart> foundCart = cartRepo.findById(id);
    return foundCart.get();
  }
}
