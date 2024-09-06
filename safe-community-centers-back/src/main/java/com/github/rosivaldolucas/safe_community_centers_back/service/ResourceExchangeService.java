package com.github.rosivaldolucas.safe_community_centers_back.service;

import com.github.rosivaldolucas.safe_community_centers_back.dto.ResourceExchangeRequestDTO;
import com.github.rosivaldolucas.safe_community_centers_back.entity.CommunityCenter;
import com.github.rosivaldolucas.safe_community_centers_back.entity.ResourceExchangeHistory;
import com.github.rosivaldolucas.safe_community_centers_back.repository.ResourceExchangeHistoryRepository;
import org.springframework.stereotype.Service;

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

}
