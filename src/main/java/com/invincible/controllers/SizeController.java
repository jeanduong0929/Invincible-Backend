package com.invincible.controllers;

import com.invincible.dtos.requests.SizeRequest;
import com.invincible.entities.Size;
import com.invincible.services.SizeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/size")
public class SizeController {
  private SizeService sizeService;

  public SizeController(SizeService sizeService) {
    this.sizeService = sizeService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Size addSize(@RequestBody SizeRequest req) {
    return sizeService.createSize(req);
  }
}
