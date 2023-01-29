package com.invincible.services;

import com.invincible.dtos.requests.QuantityRequest;
import com.invincible.entities.Quantity;
import com.invincible.entities.Size;
import com.invincible.repositories.QuantityRepository;
import com.invincible.repositories.SizeRepository;
import com.invincible.utils.custom_exceptions.SizeNotFoundException;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class QuantityService {
  private final QuantityRepository quantRepo;
  private final SizeRepository sizeRepo;

  public QuantityService(QuantityRepository quantRepo,
                         SizeRepository sizeRepo) {
    this.quantRepo = quantRepo;
    this.sizeRepo = sizeRepo;
  }

  public Quantity addQuantity(QuantityRequest req) {
    Optional<Size> sizes = sizeRepo.findAll()
                               .stream()
                               .filter(s -> s.getId().equals(req.getSize_id()))
                               .findFirst();

    if (sizes.isEmpty())
      throw new SizeNotFoundException("There is no size with that id");

    Quantity newQuant = new Quantity(req);
    quantRepo.save(newQuant);
    quantRepo.linkSizesQuantity(sizes.get().getId(), newQuant.getId());
    return newQuant;
  }
}
