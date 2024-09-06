package com.github.rosivaldolucas.safe_community_centers_back.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Document(collection = "community_center")
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

  private LocalDateTime createdAt;

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
    this.createdAt = LocalDateTime.now();

    this.occupancyPercentage = this.calculateOccupancyPercentage(this.maxCapacity, this.currentOccupancy);
  }

  public void updateOccupancy(int newOccupancy) {
    if (newOccupancy > this.maxCapacity) {
      throw new IllegalArgumentException("Maximum capacity exceeded");
    }

    this.currentOccupancy = newOccupancy;
    this.occupancyPercentage = this.calculateOccupancyPercentage(this.maxCapacity, this.currentOccupancy);
  }

  public void containsResources(Set<Resource> resources) {
    for (Resource resource : resources) {
      if (!this.resources.contains(resource)) {
        throw new IllegalArgumentException("Resource does not exist");
      }
    }
  }

  public void addResources(Set<Resource> resourcesToAdd) {
    Set<Resource> newResourcesAdded = new HashSet<>();

    for (Resource resourceToAdd : resourcesToAdd) {
      this.resources.forEach(resource -> {
        if (resource.getType().equals(resourceToAdd.getType())) {
          resource.addQuantity(resourceToAdd.getQuantity());
        } else {
          newResourcesAdded.add(resourceToAdd);
        }
      });
    }

    this.resources.addAll(newResourcesAdded);
  }

  public void removeResources(Set<Resource> resourcesToRemove) {
    Set<Resource> resourcesRemoved = new HashSet<>();

    for (Resource resourceToRemove : resourcesToRemove) {
      this.resources.forEach(resource -> {
        if (resource.getType().equals(resourceToRemove.getType())) {
          resource.removeQuantity(resourceToRemove.getQuantity());

          if (resource.getQuantity() <= 0) {
            resourcesRemoved.add(resource);
          }
        }
      });
    }

    this.resources.removeAll(resourcesRemoved);
  }

  private double calculateOccupancyPercentage(int maxCapacity, int currentOccupancy) {
    return ((double) (100 * currentOccupancy) / maxCapacity);
  }

  public String getId() {
    return id;
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

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
}
