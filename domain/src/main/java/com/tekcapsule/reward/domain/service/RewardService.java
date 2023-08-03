package com.tekcapsule.reward.domain.service;

import com.tekcapsule.reward.domain.model.RewardSummary;
import com.tekcapsule.reward.domain.command.CreateCommand;
import com.tekcapsule.reward.domain.command.RecommendCommand;
import com.tekcapsule.reward.domain.command.UpdateCommand;

import java.util.List;


public interface RewardService {

    void create(CreateCommand createCommand);

    void update(UpdateCommand updateCommand);

    List<RewardSummary> findAll();

    List<RewardSummary> findAllByTopicCode(String code);
    void recommend(RecommendCommand recommendCommand);
}
