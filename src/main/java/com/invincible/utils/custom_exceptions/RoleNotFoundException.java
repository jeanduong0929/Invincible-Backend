package com.invincible.utils.custom_exceptions;

public class RoleNotFoundException extends RuntimeException {
  public RoleNotFoundException(String message) { super(message); }
}
