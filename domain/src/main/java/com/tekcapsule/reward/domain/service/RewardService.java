package com.tekcapsule.reward.domain.service;

import com.tekcapsule.reward.domain.model.RewardSummary;
import com.tekcapsule.reward.domain.command.CreateContributionCommand;
import com.tekcapsule.reward.domain.command.ClaimRewardsCommand;
import com.tekcapsule.reward.domain.command.UpdateContributionCommand;

import java.util.List;


public interface RewardService {

    void create(CreateContributionCommand createContributionCommand);

    void update(UpdateContributionCommand updateContributionCommand);

    List<RewardSummary> findAll();

    List<RewardSummary> findAllByTopicCode(String code);
    void recommend(ClaimRewardsCommand claimRewardsCommand);
}
