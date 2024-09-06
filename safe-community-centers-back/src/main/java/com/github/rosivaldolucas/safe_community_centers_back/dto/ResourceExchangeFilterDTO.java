package com.github.rosivaldolucas.safe_community_centers_back.dto;

import java.time.LocalDateTime;

public record ResourceExchangeFilterDTO(
        LocalDateTime start,
        LocalDateTime end
) {
}
