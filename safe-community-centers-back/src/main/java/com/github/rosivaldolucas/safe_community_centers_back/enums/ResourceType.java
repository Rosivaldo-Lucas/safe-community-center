package com.github.rosivaldolucas.safe_community_centers_back.enums;

public enum ResourceType {

  DOCTOR(4),
  VOLUNTEER(3),
  MEDICAL_SUPPLY_KIT(7),
  TRANSPORT_VEHICLE(5),
  FOOD_BASKET(2);

  private final Integer points;

  ResourceType(Integer points) {
    this.points = points;
  }

  public String getResourceTypeName() {
    return name();
  }

  public Integer getPoints() {
    return points;
  }

}
