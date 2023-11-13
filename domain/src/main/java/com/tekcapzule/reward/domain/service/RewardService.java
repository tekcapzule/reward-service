package com.tekcapzule.reward.domain.service;

import com.tekcapzule.reward.domain.command.*;
import com.tekcapzule.reward.domain.command.RedeemPointsCommand;
import com.tekcapzule.reward.domain.model.Reward;
import com.tekcapzule.reward.domain.command.AwardPointsCommand;

import java.util.List;


public interface RewardService {
    Reward findByUserId(String userId);
    void award(AwardPointsCommand awardPointsCommand);

    void redeem(RedeemPointsCommand redeemPointsCommand);

    List<Reward> getLeaderboard();
}
