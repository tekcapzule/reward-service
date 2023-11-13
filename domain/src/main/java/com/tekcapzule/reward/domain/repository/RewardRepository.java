package com.tekcapzule.reward.domain.repository;

import com.tekcapzule.reward.domain.model.Reward;
import com.tekcapzule.core.domain.CrudRepository;

import java.util.List;

public interface RewardRepository extends CrudRepository<Reward, String> {

    List<Reward> findTopContributors();
}
