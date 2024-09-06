package com.github.rosivaldolucas.safe_community_centers_back.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateOccupancyCommunityCenterDTO(
        @NotNull
        @PositiveOrZero
        Integer newOccupancy
) {
}
