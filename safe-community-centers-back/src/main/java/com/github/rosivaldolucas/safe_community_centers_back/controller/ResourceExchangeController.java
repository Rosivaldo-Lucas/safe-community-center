package com.github.rosivaldolucas.safe_community_centers_back.controller;

import com.github.rosivaldolucas.safe_community_centers_back.dto.ResourceExchangeRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/community-centers/resource-exchange")
public class ResourceExchangeController {

  @PostMapping("/request")
  public ResponseEntity<Void> requestResourceExchange(@RequestBody @Valid ResourceExchangeRequestDTO resourceExchangeRequestDTO) {

  }

//  @PostMapping("/accept/{communityCenterId}")
//  public ResponseEntity<Void> acceptResourceExchange(@PathVariable UUID communityCenterId) {
//
//  }
//
//  @PostMapping("/reject/{communityCenterId}")
//  public ResponseEntity<Void> rejectResourceExchange(@PathVariable UUID communityCenterId) {
//
//  }

}
