package com.tekcapsule.reward.domain.service;

import com.tekcapsule.reward.domain.command.*;
import com.tekcapsule.reward.domain.model.Reward;

import java.util.List;


public interface RewardService {
    Reward findByUserId(String userId);
    void award(AwardPointsCommand awardPointsCommand);

    void redeem(RedeemPointsCommand redeemPointsCommand);

    List<Reward> getLeaderboard();
}
