package com.github.rosivaldolucas.safe_community_centers_back.controller;

import com.github.rosivaldolucas.safe_community_centers_back.entity.CommunityCenter;
import com.github.rosivaldolucas.safe_community_centers_back.service.CommunityCenterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/community-centers/reports")
public class ReportController {

  private final CommunityCenterService communityCenterService;

  public ReportController(CommunityCenterService communityCenterService) {
    this.communityCenterService = communityCenterService;
  }

  @GetMapping("/high-occupancy")
  public ResponseEntity<Page<CommunityCenter>> listCommunityCentersHighOccupancy(
          @RequestParam(defaultValue = "90") double minOccupancyPercentage,
          Pageable pageable
  ) {
    Page<CommunityCenter> communityCenters = this.communityCenterService.listCommunityCentersHighOccupancy(minOccupancyPercentage, pageable);

    return ResponseEntity.status(HttpStatus.OK).body(communityCenters);
  }

}
