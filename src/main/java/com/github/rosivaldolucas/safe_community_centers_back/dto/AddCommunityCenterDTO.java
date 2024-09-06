package com.github.rosivaldolucas.safe_community_centers_back.dto;

import com.github.rosivaldolucas.safe_community_centers_back.entity.Address;
import com.github.rosivaldolucas.safe_community_centers_back.entity.Resource;
import jakarta.validation.constraints.*;

import java.util.Set;

public record AddCommunityCenterDTO(
        @NotBlank
        String name,

        @NotBlank
        String description,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 11, max = 11)
        String phone,

        @NotNull
        Address address,

        @NotNull
        @PositiveOrZero
        Integer maxCapacity,

        @NotNull
        @PositiveOrZero
        Integer currentOccupancy,

        @NotNull
        Set<Resource> resources
) {
}
