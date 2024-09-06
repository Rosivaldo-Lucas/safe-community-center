package com.github.rosivaldolucas.safe_community_centers_back.controller;

import com.github.rosivaldolucas.safe_community_centers_back.dto.AddCommunityCenterDTO;
import com.github.rosivaldolucas.safe_community_centers_back.service.CommunityCenterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("community-centers")
public class CommunityCenterController {

  private final CommunityCenterService communityCenterService;

  public CommunityCenterController(CommunityCenterService communityCenterService) {
    this.communityCenterService = communityCenterService;
  }

  @PostMapping
  public ResponseEntity<Void> createCommunityCenter(@RequestBody @Valid AddCommunityCenterDTO addCommunityCenterDTO) {
    this.communityCenterService.createCommunityCenter(addCommunityCenterDTO);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
