package com.github.rosivaldolucas.safe_community_centers_back.entity;

import com.github.rosivaldolucas.safe_community_centers_back.enums.ResourceType;

import java.util.Objects;

public class Resource {

  private ResourceType type;
  private Integer quantity;

  public Resource(ResourceType type, Integer quantity) {
    this.type = type;
    this.quantity = quantity;
  }

  public void addQuantity(int quantityToAdd) {
    this.quantity += quantityToAdd;
  }

  public void removeQuantity(int quantityToRemove) {
    this.quantity -= quantityToRemove;
  }

  public ResourceType getType() {
    return type;
  }

  public Integer getQuantity() {
    return quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Resource resource = (Resource) o;
    return type == resource.type;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(type);
  }
}
