package com.github.rosivaldolucas.safe_community_centers_back.entity;

import com.github.rosivaldolucas.safe_community_centers_back.enums.ResourceExchangeStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

  public ResourceExchangeHistory(String requesterCommunityCenterId, String receiverCommunityCenterId, Set<Resource> offeredResources, Set<Resource> requestedResources) {
    this.id = UUID.randomUUID().toString();
    this.requesterCommunityCenterId = requesterCommunityCenterId;
    this.receiverCommunityCenterId = receiverCommunityCenterId;
    this.offeredResources = offeredResources;
    this.requestedResources = requestedResources;
    this.status = ResourceExchangeStatus.PENDING;
  }

  public String getId() {
    return id;
  }

  public String getRequesterCommunityCenterId() {
    return requesterCommunityCenterId;
  }

  public String getReceiverCommunityCenterId() {
    return receiverCommunityCenterId;
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
}
