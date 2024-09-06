package com.github.rosivaldolucas.safe_community_centers_back.repository;

import com.github.rosivaldolucas.safe_community_centers_back.entity.ResourceExchangeHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceExchangeHistoryRepository extends MongoRepository<ResourceExchangeHistory, String> {
}
