package com.github.rosivaldolucas.safe_community_centers_back.dto;

import com.github.rosivaldolucas.safe_community_centers_back.entity.Resource;

import java.util.Set;

public record ResourceExchangeHistoryDTO(
        String id,
        String requesterCommunityCenterId,
        String receiverCommunityCenterId,
        Set<Resource>offeredResources,
        Set<Resource> requestedResources
) {
}
