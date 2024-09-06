package com.github.rosivaldolucas.safe_community_centers_back.service;

import com.github.rosivaldolucas.safe_community_centers_back.dto.AddCommunityCenterDTO;
import com.github.rosivaldolucas.safe_community_centers_back.entity.CommunityCenter;
import com.github.rosivaldolucas.safe_community_centers_back.repository.CommunityCenterRepository;
import org.springframework.stereotype.Service;

@Service
public class CommunityCenterService {

  private final CommunityCenterRepository communityCenterRepository;

  public CommunityCenterService(CommunityCenterRepository communityCenterRepository) {
    this.communityCenterRepository = communityCenterRepository;
  }

  public void createCommunityCenter(AddCommunityCenterDTO addCommunityCenterDTO) {
    CommunityCenter newCommunityCenter = new CommunityCenter(
            addCommunityCenterDTO.name(),
            addCommunityCenterDTO.description(),
            addCommunityCenterDTO.email(),
            addCommunityCenterDTO.phone(),
            addCommunityCenterDTO.address(),
            addCommunityCenterDTO.maxCapacity(),
            addCommunityCenterDTO.currentOccupancy(),
            addCommunityCenterDTO.resources()
    );

    this.communityCenterRepository.save(newCommunityCenter);
  }

}
