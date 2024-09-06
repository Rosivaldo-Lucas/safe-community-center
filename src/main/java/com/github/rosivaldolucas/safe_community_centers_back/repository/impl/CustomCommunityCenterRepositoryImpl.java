package com.github.rosivaldolucas.safe_community_centers_back.repository.impl;

import com.github.rosivaldolucas.safe_community_centers_back.dto.AverageResourceCommunityCenterDTO;
import com.github.rosivaldolucas.safe_community_centers_back.entity.CommunityCenter;
import com.github.rosivaldolucas.safe_community_centers_back.repository.CustomCommunityCenterRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CustomCommunityCenterRepositoryImpl implements CustomCommunityCenterRepository {

  private final MongoTemplate mongoTemplate;

  public CustomCommunityCenterRepositoryImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public List<AverageResourceCommunityCenterDTO> calculateAverageResourcesPerCommunityCenter() {
    Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.unwind("resources"),
            Aggregation.group("resources.type").avg("resources.quantity").as("averageQuantity")
    );

    AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, CommunityCenter.class, Map.class);

    List<AverageResourceCommunityCenterDTO> resourceAverageList = new ArrayList<>();

    results.getMappedResults().forEach(result -> {
      String resourceType = (String) result.get("_id");
      Double average = (Double) result.get("averageQuantity");

      resourceAverageList.add(new AverageResourceCommunityCenterDTO(resourceType, average));
    });

    return resourceAverageList;
  }

}
