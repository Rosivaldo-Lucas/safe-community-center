package com.github.rosivaldolucas.safe_community_centers_back.service;

import com.github.rosivaldolucas.safe_community_centers_back.dto.ResourceExchangeRequestDTO;
import com.github.rosivaldolucas.safe_community_centers_back.entity.CommunityCenter;
import com.github.rosivaldolucas.safe_community_centers_back.entity.Resource;
import com.github.rosivaldolucas.safe_community_centers_back.entity.ResourceExchangeHistory;
import com.github.rosivaldolucas.safe_community_centers_back.repository.ResourceExchangeHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ResourceExchangeService {

  private final ResourceExchangeHistoryRepository resourceExchangeHistoryRepository;
  private final CommunityCenterService communityCenterService;

  public ResourceExchangeService(ResourceExchangeHistoryRepository resourceExchangeHistoryRepository, CommunityCenterService communityCenterService) {
    this.resourceExchangeHistoryRepository = resourceExchangeHistoryRepository;
    this.communityCenterService = communityCenterService;
  }

  public void requestResourceExchange(ResourceExchangeRequestDTO resourceExchangeRequestDTO) {
    CommunityCenter requesterCommunityCenter = this.communityCenterService.getCommunityCenterById(resourceExchangeRequestDTO.requesterCommunityCenterId());
    CommunityCenter receiverCommunityCenter = this.communityCenterService.getCommunityCenterById(resourceExchangeRequestDTO.receiverCommunityCenterId());

    requesterCommunityCenter.containsResources(resourceExchangeRequestDTO.offeredResources());
    receiverCommunityCenter.containsResources(resourceExchangeRequestDTO.requestedResources());

    ResourceExchangeHistory resourceExchangeHistory = new ResourceExchangeHistory(
            requesterCommunityCenter.getId(),
            receiverCommunityCenter.getId(),
            resourceExchangeRequestDTO.offeredResources(),
            resourceExchangeRequestDTO.requestedResources()
    );

    this.resourceExchangeHistoryRepository.save(resourceExchangeHistory);
  }

  public void acceptNegotiationExchange(UUID resourceExchangeId) {
    ResourceExchangeHistory resourceExchangeHistory = this.resourceExchangeHistoryRepository
            .findById(resourceExchangeId.toString())
            .orElseThrow(() -> new RuntimeException("Resource exchange not found"));

    CommunityCenter requesterCommunityCenter = this.communityCenterService.getCommunityCenterById(resourceExchangeHistory.getRequesterCommunityCenterId());
    CommunityCenter receiverCommunityCenter = this.communityCenterService.getCommunityCenterById(resourceExchangeHistory.getReceiverCommunityCenterId());

    if (!resourceExchangeHistory.isPending()) {
      throw new RuntimeException("Resource exchange is not pending");
    }

    Set<Resource> offeredResources = resourceExchangeHistory.getOfferedResources();
    Set<Resource> requestedResources = resourceExchangeHistory.getRequestedResources();

    requesterCommunityCenter.removeResources(offeredResources);
    receiverCommunityCenter.addResources(offeredResources);

    receiverCommunityCenter.removeResources(requestedResources);
    requesterCommunityCenter.addResources(requestedResources);

    this.communityCenterService.saveAllCommunityCenters(List.of(requesterCommunityCenter, receiverCommunityCenter));

    resourceExchangeHistory.accept();

    this.resourceExchangeHistoryRepository.save(resourceExchangeHistory);
  }

  public void rejectResourceExchange(UUID resourceExchangeId) {
    ResourceExchangeHistory resourceExchangeHistory = this.resourceExchangeHistoryRepository
            .findById(resourceExchangeId.toString())
            .orElseThrow(() -> new RuntimeException("Resource exchange not found"));

    resourceExchangeHistory.reject();

    this.resourceExchangeHistoryRepository.save(resourceExchangeHistory);
  }

}
