package com.tekcapsule.reward.domain.service;

import com.tekcapsule.reward.domain.command.*;
import com.tekcapsule.reward.domain.model.RewardSummary;

import java.util.List;


public interface RewardService {

    void create(CreateRewardSummary createRewardSummary);

    void update(UpdateRewardSummaryCommand updateRewardSummaryCommand);

    List<RewardSummary> findAll();

    List<RewardSummary> findAllByTopicCode(String code);
}
