package com.tekcapsule.reward.domain.repository;

import com.tekcapsule.reward.domain.model.Reward;
import com.tekcapsule.core.domain.CrudRepository;

import java.util.List;

public interface RewardRepository extends CrudRepository<Reward, String> {

    List<Reward> findAllByTopicCode(String topicCode);
}
