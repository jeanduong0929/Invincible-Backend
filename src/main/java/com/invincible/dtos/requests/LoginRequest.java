package com.invincible.dtos.requests;

import lombok.ToString;

@ToString
public class LoginRequest {
  // FIELDS
  private String username;
  private String password;

  // CONSTRUCTORS
  public LoginRequest() {}

  public LoginRequest(String username, String password) {
    this.username = username;
    this.password = password;
  }

  // GETTERS/SETTERS
  public String getUsername() { return username; }

  public void setUsername(String username) { this.username = username; }

  public String getPassword() { return password; }

  public void setPassword(String password) { this.password = password; }
}
