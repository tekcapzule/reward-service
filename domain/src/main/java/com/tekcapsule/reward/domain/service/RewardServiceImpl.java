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
    public void create(AwardPointsCommand awardPointsCommand) {

        log.info(String.format("Entering create reward service - Module Code :%s", awardPointsCommand.getTopicCode()));
        Reward reward = Reward.builder()
                .status(Status.ACTIVE)
                .build();

        reward.setAddedOn(awardPointsCommand.getExecOn());
        reward.setAddedBy(awardPointsCommand.getExecBy().getUserId());

        rewardRepository.save(reward);
    }

    @Override
    public void update(UpdatePointsCommand updatePointsCommand) {

        log.info(String.format("Entering update reward service - Reward ID:%s", updatePointsCommand.getTopicCode()));

        Reward reward = rewardRepository.findBy(updatePointsCommand.getUserId());
        if (reward != null) {
            reward.setUpdatedOn(updatePointsCommand.getExecOn());
            reward.setUpdatedBy(updatePointsCommand.getExecBy().getUserId());
            rewardRepository.save(reward);
        }
    }

    @Override
    public List<Reward> findAll() {

        log.info("Entering findAll Reward service");

        return rewardRepository.findAll();
    }

    @Override
    public List<Reward> findAllByTopicCode(String topicCode) {

        log.info(String.format("Entering findAllByTopicCode Reward service - Module code:%s", topicCode));

        return rewardRepository.findAllByTopicCode(topicCode);
    }


}
