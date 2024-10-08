package com.github.rosivaldolucas.safe_community_centers_back.repository;

import com.github.rosivaldolucas.safe_community_centers_back.entity.ResourceExchangeHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ResourceExchangeHistoryRepository extends MongoRepository<ResourceExchangeHistory, String> {

  Page<ResourceExchangeHistory> findByUpdatedAtBetween(
          LocalDateTime start,
          LocalDateTime end,
          Pageable pageable
  );

  Page<ResourceExchangeHistory> findByRequesterCommunityCenterIdOrReceiverCommunityCenterId(
          String requesterCommunityCenterId,
          String receiverCommunityCenterId,
          Pageable pageable
  );

}
