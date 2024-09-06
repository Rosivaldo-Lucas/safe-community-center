package com.github.rosivaldolucas.safe_community_centers_back.dto;

import com.github.rosivaldolucas.safe_community_centers_back.entity.Resource;
import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public record ResourceExchangeRequestDTO(
        @NotNull
        UUID requesterCommunityCenterId,

        @NotNull
        UUID receiverCommunityCenterId,

        @NotNull
        Set<Resource> offeredResources,

        @NotNull
        Set<Resource> requestedResources
) {
}
