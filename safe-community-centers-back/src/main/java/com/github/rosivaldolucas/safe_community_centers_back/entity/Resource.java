package com.github.rosivaldolucas.safe_community_centers_back.entity;

import com.github.rosivaldolucas.safe_community_centers_back.enums.ResourceType;

public class Resource {

  private ResourceType type;
  private Integer quantity;

  public ResourceType getType() {
    return type;
  }

  public Integer getQuantity() {
    return quantity;
  }
}
