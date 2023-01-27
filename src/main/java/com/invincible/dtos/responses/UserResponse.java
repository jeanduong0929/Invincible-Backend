package com.invincible.dtos.responses;

import com.invincible.entities.User;
import lombok.ToString;

@ToString
public class UserResponse {
  // FIELDS
  private String id;
  private String username;
  private String email;
  private String role;

  // CONSTRUCTORS
  public UserResponse() {}

  public UserResponse(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.email = user.getEmail();
    this.role = user.getRole().getRole();
  }

  public UserResponse(String id, String username, String email, String role) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.role = role;
  }

  // GETTERS/SETTERS
  public String getId() { return id; }

  public void setId(String id) { this.id = id; }

  public String getUsername() { return username; }

  public void setUsername(String username) { this.username = username; }

  public String getEmail() { return email; }

  public void setEmail(String email) { this.email = email; }

  public String getRole() { return role; }

  public void setRole(String role) { this.role = role; }
}
