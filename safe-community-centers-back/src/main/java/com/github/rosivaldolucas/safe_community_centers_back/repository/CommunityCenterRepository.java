package com.github.rosivaldolucas.safe_community_centers_back.repository;

import com.github.rosivaldolucas.safe_community_centers_back.entity.CommunityCenter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityCenterRepository extends MongoRepository<CommunityCenter, String> {

  Page<CommunityCenter> findByOccupancyPercentageGreaterThanEqual(Double minOccupancyPercentage, Pageable pageable);

}
