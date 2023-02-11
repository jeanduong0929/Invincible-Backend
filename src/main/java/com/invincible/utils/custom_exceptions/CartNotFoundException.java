package com.invincible.utils.custom_exceptions;

/**
 * CartNotFoundException
 */
public class CartNotFoundException extends RuntimeException {
  public CartNotFoundException() {}
  public CartNotFoundException(String msg) { super(msg); }
}
