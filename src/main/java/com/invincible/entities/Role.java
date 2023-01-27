package com.invincible.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import lombok.ToString;

@ToString
@Entity
@Table(name = "in_role")
public class Role {
  // FIELDS
  @Id private String id;

  @Column(name = "role", nullable = false) private String role;

  @OneToMany(mappedBy = "role", fetch = FetchType.EAGER,
             cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<User> users;

  // CONSTRUCTORS
  public Role() {}

  public Role(String id, String role, List<User> users) {
    this.id = id;
    this.role = role;
    this.users = users;
  }

  // GETTERS/SETTERS
  public String getId() { return id; }

  public void setId(String id) { this.id = id; }

  public String getRole() { return role; }

  public void setRole(String role) { this.role = role; }

  public List<User> getUsers() { return users; }

  public void setUsers(List<User> users) { this.users = users; }
}
