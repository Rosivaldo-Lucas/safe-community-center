package com.github.rosivaldolucas.safe_community_centers_back.dto;

import java.util.UUID;

public record NotificationFullyOccupiedDTO(
        UUID id,
        String name,
        Integer maxCapacity
) {
}
