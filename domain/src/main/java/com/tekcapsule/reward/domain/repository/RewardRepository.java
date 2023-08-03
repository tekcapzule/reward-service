package com.tekcapsule.reward.domain.repository;

import com.tekcapsule.reward.domain.model.RewardSummary;
import com.tekcapsule.core.domain.CrudRepository;

import java.util.List;

public interface RewardRepository extends CrudRepository<RewardSummary, String> {

    List<RewardSummary> findAllByTopicCode(String topicCode);
}
