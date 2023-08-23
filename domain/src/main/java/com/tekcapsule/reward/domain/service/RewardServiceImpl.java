package com.tekcapsule.reward.domain.service;

import com.tekcapsule.reward.domain.command.*;
import com.tekcapsule.reward.domain.model.Reward;
import com.tekcapsule.reward.domain.model.Status;
import com.tekcapsule.reward.domain.repository.RewardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class RewardServiceImpl implements RewardService {
    private RewardRepository rewardRepository;

    @Autowired
    public RewardServiceImpl(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }


    @Override
    public void award(AwardPointsCommand awardPointsCommand) {

        log.info(String.format("Entering award reward points service - userId :%s", awardPointsCommand.getUserId()));
        Reward reward = Reward.builder()
                .status(Status.ACTIVE)
                .build();

        reward.setAddedOn(awardPointsCommand.getExecOn());
        reward.setAddedBy(awardPointsCommand.getExecBy().getUserId());

        rewardRepository.save(reward);
    }

    @Override
    public void redeem(RedeemPointsCommand redeemPointsCommand) {

        log.info(String.format("Entering redeem reward points service - userId:%s", redeemPointsCommand.getUserId()));

        Reward reward = rewardRepository.findBy(redeemPointsCommand.getUserId());
        if (reward != null) {
            reward.setUpdatedOn(redeemPointsCommand.getExecOn());
            reward.setUpdatedBy(redeemPointsCommand.getExecBy().getUserId());
            rewardRepository.save(reward);
        }
    }

    @Override
    public Reward findByUserId(String userId) {
        log.info("Entering findByUserId Reward service");
        return rewardRepository.findBy(userId);
    }

    @Override
    public List<Reward> getLeaderboard() {
        log.info("Entering getLeaderboard Reward service");
        return rewardRepository.findTopContributors();
    }

}
