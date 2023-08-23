package com.tekcapsule.reward.domain.service;

import com.tekcapsule.reward.domain.command.*;
import com.tekcapsule.reward.domain.model.Reward;

import java.util.List;


public interface RewardService {

    void create(AwardPointsCommand awardPointsCommand);

    void update(UpdatePointsCommand updatePointsCommand);

    List<Reward> findAll();

    List<Reward> findAllByTopicCode(String code);
}
