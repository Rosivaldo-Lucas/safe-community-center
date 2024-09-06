package com.github.rosivaldolucas.safe_community_centers_back.controller;

import com.github.rosivaldolucas.safe_community_centers_back.dto.AverageResourceCommunityCenterDTO;
import com.github.rosivaldolucas.safe_community_centers_back.entity.CommunityCenter;
import com.github.rosivaldolucas.safe_community_centers_back.entity.ResourceExchangeHistory;
import com.github.rosivaldolucas.safe_community_centers_back.service.CommunityCenterService;
import com.github.rosivaldolucas.safe_community_centers_back.service.ResourceExchangeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/community-centers/reports")
public class ReportController {

  private final CommunityCenterService communityCenterService;
  private final ResourceExchangeService resourceExchangeService;

  public ReportController(CommunityCenterService communityCenterService, ResourceExchangeService resourceExchangeService) {
    this.communityCenterService = communityCenterService;
    this.resourceExchangeService = resourceExchangeService;
  }

  @GetMapping("/high-occupancy")
  public ResponseEntity<Page<CommunityCenter>> listCommunityCentersHighOccupancy(@RequestParam(defaultValue = "90") double minOccupancyPercentage, Pageable pageable) {
    Page<CommunityCenter> communityCenters = this.communityCenterService.listCommunityCentersHighOccupancy(minOccupancyPercentage, pageable);

    return ResponseEntity.status(HttpStatus.OK).body(communityCenters);
  }

  @GetMapping("/average-resources")
  public ResponseEntity<List<AverageResourceCommunityCenterDTO>> listAverageResources() {
    List<AverageResourceCommunityCenterDTO> averageResourcesPerCommunityCenter = this.communityCenterService.listAverageResources();

    return ResponseEntity.status(HttpStatus.OK).body(averageResourcesPerCommunityCenter);
  }

  @GetMapping("/resource-exchange-history/{communityCenterId}")
  public ResponseEntity<Page<ResourceExchangeHistory>> listResourceExchangeHistory(@PathVariable UUID communityCenterId, Pageable pageable) {
    Page<ResourceExchangeHistory> resourceExchangeHistories = this.resourceExchangeService.listResourceExchangeHistories(communityCenterId, pageable);

    return ResponseEntity.status(HttpStatus.OK).body(resourceExchangeHistories);
  }

}
