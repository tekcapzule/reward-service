package com.tekcapsule.reward.domain.service;

import com.tekcapsule.reward.domain.command.*;
import com.tekcapsule.reward.domain.model.RewardSummary;
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
    public void create(CreateRewardSummary createRewardSummary) {

        log.info(String.format("Entering create rewardSummary service - Module Code :%s", createRewardSummary.getTopicCode()));
        RewardSummary rewardSummary = RewardSummary.builder()
                .status(Status.ACTIVE)
                .build();

        rewardSummary.setAddedOn(createRewardSummary.getExecOn());
        rewardSummary.setAddedBy(createRewardSummary.getExecBy().getUserId());

        rewardRepository.save(rewardSummary);
    }

    @Override
    public void update(UpdateRewardSummaryCommand updateRewardSummaryCommand) {

        log.info(String.format("Entering update rewardSummary service - RewardSummary ID:%s", updateRewardSummaryCommand.getTopicCode()));

        RewardSummary rewardSummary = rewardRepository.findBy(updateRewardSummaryCommand.getUserId());
        if (rewardSummary != null) {
            rewardSummary.setUpdatedOn(updateRewardSummaryCommand.getExecOn());
            rewardSummary.setUpdatedBy(updateRewardSummaryCommand.getExecBy().getUserId());
            rewardRepository.save(rewardSummary);
        }
    }

    @Override
    public List<RewardSummary> findAll() {

        log.info("Entering findAll RewardSummary service");

        return rewardRepository.findAll();
    }

    @Override
    public List<RewardSummary> findAllByTopicCode(String topicCode) {

        log.info(String.format("Entering findAllByTopicCode RewardSummary service - Module code:%s", topicCode));

        return rewardRepository.findAllByTopicCode(topicCode);
    }


}
