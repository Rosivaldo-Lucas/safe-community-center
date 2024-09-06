package com.github.rosivaldolucas.safe_community_centers_back.controller;

import com.github.rosivaldolucas.safe_community_centers_back.dto.AddCommunityCenterDTO;
import com.github.rosivaldolucas.safe_community_centers_back.dto.UpdateOccupancyCommunityCenterDTO;
import com.github.rosivaldolucas.safe_community_centers_back.entity.CommunityCenter;
import com.github.rosivaldolucas.safe_community_centers_back.service.CommunityCenterService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("community-centers")
public class CommunityCenterController {

  private final CommunityCenterService communityCenterService;

  public CommunityCenterController(CommunityCenterService communityCenterService) {
    this.communityCenterService = communityCenterService;
  }

  @GetMapping
  public ResponseEntity<Page<CommunityCenter>> listCommunityCenters(Pageable pageable) {
    Page<CommunityCenter> communityCenters = communityCenterService.listCommunityCenters(pageable);

    return ResponseEntity.status(HttpStatus.OK).body(communityCenters);
  }

  @GetMapping("/{communityCenterId}")
  public ResponseEntity<CommunityCenter> getCommunityCenter(@PathVariable UUID communityCenterId) {
    CommunityCenter communityCenter = this.communityCenterService.getCommunityCenterById(communityCenterId);

    return ResponseEntity.status(HttpStatus.OK).body(communityCenter);
  }

  @PostMapping
  public ResponseEntity<Void> createCommunityCenter(@RequestBody @Valid AddCommunityCenterDTO addCommunityCenterDTO) {
    this.communityCenterService.createCommunityCenter(addCommunityCenterDTO);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PatchMapping("/{communityCenterId}/update-occupancy")
  public ResponseEntity<Void> updateOccupancyCommunityCenter(@PathVariable UUID communityCenterId, @RequestBody @Valid UpdateOccupancyCommunityCenterDTO updateOccupancyDTO) {
    this.communityCenterService.updateOccupancyCommunityCenter(communityCenterId, updateOccupancyDTO);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
