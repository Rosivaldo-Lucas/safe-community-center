package com.github.rosivaldolucas.safe_community_centers_back.entity;

import com.github.rosivaldolucas.safe_community_centers_back.enums.ResourceExchangeStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Document(collection = "resource_exchange_history")
public class ResourceExchangeHistory {

  @Id
  private String id;
  private String requesterCommunityCenterId;
  private String receiverCommunityCenterId;
  private Set<Resource> offeredResources;
  private Set<Resource> requestedResources;
  private ResourceExchangeStatus status;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public ResourceExchangeHistory(String requesterCommunityCenterId, String receiverCommunityCenterId, Set<Resource> offeredResources, Set<Resource> requestedResources) {
    this.id = UUID.randomUUID().toString();
    this.requesterCommunityCenterId = requesterCommunityCenterId;
    this.receiverCommunityCenterId = receiverCommunityCenterId;
    this.offeredResources = offeredResources;
    this.requestedResources = requestedResources;
    this.status = ResourceExchangeStatus.PENDING;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public void accept() {
    if (!this.status.equals(ResourceExchangeStatus.PENDING)) {
      throw new IllegalStateException("Negotiation cannot be accepted because it is already " + this.status);
    }

    this.status = ResourceExchangeStatus.ACCEPTED;
    this.updatedAt = LocalDateTime.now();
  }

  public void reject() {
    if (!this.status.equals(ResourceExchangeStatus.ACCEPTED)) {
      throw new IllegalStateException("Negotiation cannot be rejected because it is already " + this.status);
    }

    this.status = ResourceExchangeStatus.REJECTED;
    this.updatedAt = LocalDateTime.now();
  }

  public boolean isPending() {
    return this.status.equals(ResourceExchangeStatus.PENDING);
  }

  public UUID getId() {
    return UUID.fromString(this.id);
  }

  public UUID getRequesterCommunityCenterId() {
    return UUID.fromString(requesterCommunityCenterId);
  }

  public UUID getReceiverCommunityCenterId() {
    return UUID.fromString(receiverCommunityCenterId);
  }

  public Set<Resource> getOfferedResources() {
    return offeredResources;
  }

  public Set<Resource> getRequestedResources() {
    return requestedResources;
  }

  public ResourceExchangeStatus getStatus() {
    return status;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
