package com.github.rosivaldolucas.safe_community_centers_back.controller;

import com.github.rosivaldolucas.safe_community_centers_back.dto.ResourceExchangeFilterDTO;
import com.github.rosivaldolucas.safe_community_centers_back.dto.ResourceExchangeRequestDTO;
import com.github.rosivaldolucas.safe_community_centers_back.entity.ResourceExchangeHistory;
import com.github.rosivaldolucas.safe_community_centers_back.service.ResourceExchangeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/community-centers/resource-exchange")
public class ResourceExchangeController {

  private final ResourceExchangeService resourceExchangeService;

  public ResourceExchangeController(ResourceExchangeService resourceExchangeService) {
    this.resourceExchangeService = resourceExchangeService;
  }

  @GetMapping
  public ResponseEntity<Page<ResourceExchangeHistory>> listResourceExchanges(ResourceExchangeFilterDTO filterDTO, Pageable pageable) {
    Page<ResourceExchangeHistory> resourceExchangeHistories = this.resourceExchangeService.listResourceExchanges(filterDTO, pageable);

    return ResponseEntity.status(HttpStatus.OK).body(resourceExchangeHistories);
  }

  @PostMapping("/request")
  public ResponseEntity<Void> requestResourceExchange(@RequestBody @Valid ResourceExchangeRequestDTO resourceExchangeRequestDTO) {
    this.resourceExchangeService.requestResourceExchange(resourceExchangeRequestDTO);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PostMapping("/accept/{resourceExchangeId}")
  public ResponseEntity<Void> acceptResourceExchange(@PathVariable UUID resourceExchangeId) {
    this.resourceExchangeService.acceptNegotiationExchange(resourceExchangeId);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PostMapping("/reject/{resourceExchangeId}")
  public ResponseEntity<Void> rejectResourceExchange(@PathVariable UUID resourceExchangeId) {
    this.resourceExchangeService.rejectResourceExchange(resourceExchangeId);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
