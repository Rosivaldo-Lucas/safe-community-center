package com.github.rosivaldolucas.safe_community_centers_back.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Document(collection = "community-center")
public class CommunityCenter implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  private String id;
  private String name;
  private String description;
  private String email;
  private String phone;
  private Address address;
  private Integer maxCapacity;
  private Integer currentOccupancy;
  private Double occupancyPercentage;
  private Set<Resource> resources;

  public CommunityCenter(String name, String description, String email, String phone, Address address,
                         Integer maxCapacity, Integer currentOccupancy, Set<Resource> resources
  ) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.description = description;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.maxCapacity = maxCapacity;
    this.currentOccupancy = currentOccupancy;
    this.resources = resources;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public Address getAddress() {
    return address;
  }

  public Integer getMaxCapacity() {
    return maxCapacity;
  }

  public Integer getCurrentOccupancy() {
    return currentOccupancy;
  }

  public Double getOccupancyPercentage() {
    return occupancyPercentage;
  }

  public Set<Resource> getResources() {
    return resources;
  }
}
