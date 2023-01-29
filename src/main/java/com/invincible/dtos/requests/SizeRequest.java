package com.invincible.dtos.requests;

import lombok.ToString;

@ToString
public class SizeRequest {
  private String size;
  private String clothing_id;

  public SizeRequest() {}

  public SizeRequest(String size, String clothing_id) {
    this.size = size;
    this.clothing_id = clothing_id;
  }

  public String getSize() { return size; }

  public void setSize(String size) { this.size = size; }

  public String getClothing_id() { return clothing_id; }

  public void setClothing_id(String clothing_id) {
    this.clothing_id = clothing_id;
  }
}
