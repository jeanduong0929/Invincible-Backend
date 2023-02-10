package com.invincible.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.invincible.dtos.requests.RegisterRequest;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.ToString;

@ToString
@Entity
@Table(name = "in_user")
public class User {
  // FIELDS
  @Id private String id;

  @Column(name = "username", nullable = false) private String username;

  @Column(name = "email", nullable = false) private String email;

  @Column(name = "password", nullable = false) private byte[] password;

  @Column(name = "salt", nullable = false) private byte[] salt;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            mappedBy = "user")
  @JsonManagedReference
  private Cart cart;

  @ManyToOne
  @JoinColumn(name = "role_id", nullable = false)
  @JsonBackReference
  private Role role;

  // CONSTRUCTORS
  public User() {}

  public User(RegisterRequest req, Role role) {
    this.id = UUID.randomUUID().toString();
    this.username = req.getUsername();
    this.email = req.getEmail();
    this.role = role;
  }

  public User(String id, String username, String email, byte[] password,
              byte[] salt, Role role) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.salt = salt;
    this.role = role;
  }

  // GETTERS/SETTERS
  public String getId() { return id; }

  public void setId(String id) { this.id = id; }

  public String getUsername() { return username; }

  public void setUsername(String username) { this.username = username; }

  public String getEmail() { return email; }

  public void setEmail(String email) { this.email = email; }

  public byte[] getPassword() { return password; }

  public void setPassword(byte[] password) { this.password = password; }

  public byte[] getSalt() { return salt; }

  public void setSalt(byte[] salt) { this.salt = salt; }

  public Cart getCart() { return cart; }

  public void setCart(Cart cart) { this.cart = cart; }

  public Role getRole() { return role; }

  public void setRole(Role role) { this.role = role; }
}
