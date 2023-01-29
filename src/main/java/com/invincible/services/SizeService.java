package com.invincible.services;

import com.invincible.dtos.requests.SizeRequest;
import com.invincible.entities.Product;
import com.invincible.entities.Size;
import com.invincible.repositories.ProductRepository;
import com.invincible.repositories.SizeRepository;
import com.invincible.utils.custom_exceptions.ProductNotFoundException;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SizeService {
  private final SizeRepository sizeRepo;
  private final ProductRepository prodRepo;

  public SizeService(SizeRepository sizeRepo, ProductRepository prodRepo) {
    this.sizeRepo = sizeRepo;
    this.prodRepo = prodRepo;
  }

  public Size createSize(SizeRequest req) {
    Optional<Product> products =
        prodRepo.findAll()
            .stream()
            .filter(p -> p.getId().equals(req.getClothing_id()))
            .findFirst();

    if (products.isEmpty())
      throw new ProductNotFoundException("There is no product with that id");

    Size newSize = new Size(req);
    sizeRepo.save(newSize);
    sizeRepo.linkProductsSizes(products.get().getId(), newSize.getId());
    return newSize;
  }
}
