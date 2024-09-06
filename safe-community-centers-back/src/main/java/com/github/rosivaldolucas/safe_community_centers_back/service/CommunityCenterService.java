package com.github.rosivaldolucas.safe_community_centers_back.service;

import com.github.rosivaldolucas.safe_community_centers_back.dto.AddCommunityCenterDTO;
import com.github.rosivaldolucas.safe_community_centers_back.dto.AverageResourceCommunityCenterDTO;
import com.github.rosivaldolucas.safe_community_centers_back.dto.UpdateOccupancyCommunityCenterDTO;
import com.github.rosivaldolucas.safe_community_centers_back.entity.CommunityCenter;
import com.github.rosivaldolucas.safe_community_centers_back.repository.CommunityCenterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommunityCenterService {

  private final CommunityCenterRepository communityCenterRepository;

  public CommunityCenterService(CommunityCenterRepository communityCenterRepository) {
    this.communityCenterRepository = communityCenterRepository;
  }

  public Page<CommunityCenter> listCommunityCentersHighOccupancy(double minOccupancyPercentagePageable, Pageable pageable) {
    return this.communityCenterRepository.findByOccupancyPercentageGreaterThanEqual(minOccupancyPercentagePageable, pageable);
  }

  public List<AverageResourceCommunityCenterDTO> listAverageResources() {
    return this.communityCenterRepository.calculateAverageResourcesPerCommunityCenter();
  }

  public CommunityCenter getCommunityCenterById(UUID communityCenterId) {
    return this.communityCenterRepository
            .findById(communityCenterId.toString()).orElseThrow(() -> new RuntimeException("Community center not found"));
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

  public void updateOccupancyCommunityCenter(UUID communityCenterId, UpdateOccupancyCommunityCenterDTO updateOccupancyDTO) {
    CommunityCenter communityCenter = this.getCommunityCenterById(communityCenterId);

    communityCenter.updateOccupancy(updateOccupancyDTO.newOccupancy());

    this.communityCenterRepository.save(communityCenter);
  }

}
