package com.github.rosivaldolucas.safe_community_centers_back.repository;

import com.github.rosivaldolucas.safe_community_centers_back.dto.AverageResourceCommunityCenterDTO;

import java.util.List;

public interface CustomCommunityCenterRepository {

  List<AverageResourceCommunityCenterDTO> calculateAverageResourcesPerCommunityCenter();

}
