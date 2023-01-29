package com.invincible.utils.custom_exceptions;

public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException(String msg) { super(msg); }
}
