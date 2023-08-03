package com.tekcapsule.reward.domain.service;

import com.tekcapsule.reward.domain.model.RewardSummary;
import com.tekcapsule.reward.domain.model.Status;
import com.tekcapsule.reward.domain.repository.RewardRepository;
import com.tekcapsule.reward.domain.command.CreateContributionCommand;
import com.tekcapsule.reward.domain.command.ClaimRewardsCommand;
import com.tekcapsule.reward.domain.command.UpdateContributionCommand;
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
    public void create(CreateContributionCommand createContributionCommand) {

        log.info(String.format("Entering create rewardSummary service - Module Code :%s", createContributionCommand.getTopicCode()));
        RewardSummary rewardSummary = RewardSummary.builder()
                .title(createContributionCommand.getTitle())
                .topicCode(createContributionCommand.getTopicCode())
                .author(createContributionCommand.getAuthor())
                .publisher(createContributionCommand.getPublisher())
                .duration(createContributionCommand.getDuration())
                .resourceUrl(createContributionCommand.getResourceUrl())
                .summary(createContributionCommand.getSummary())
                .description(createContributionCommand.getDescription())
                .modules(createContributionCommand.getModules())
                .prizingModel(createContributionCommand.getPrizingModel())
                .contributionType(createContributionCommand.getContributionType())
                .resourceType(createContributionCommand.getResourceType())
                .imageUrl(createContributionCommand.getImageUrl())
                .promotion(createContributionCommand.getPromotion())
                .status(Status.ACTIVE)
                .recommendations(createContributionCommand.getRecommendations())
                .build();

        rewardSummary.setAddedOn(createContributionCommand.getExecOn());
        rewardSummary.setAddedBy(createContributionCommand.getExecBy().getUserId());

        rewardRepository.save(rewardSummary);
    }

    @Override
    public void update(UpdateContributionCommand updateContributionCommand) {

        log.info(String.format("Entering update rewardSummary service - RewardSummary ID:%s", updateContributionCommand.getCourseId()));

        RewardSummary rewardSummary = rewardRepository.findBy(updateContributionCommand.getCourseId());
        if (rewardSummary != null) {
            rewardSummary.setTitle(updateContributionCommand.getTitle());
            rewardSummary.setTopicCode(updateContributionCommand.getTopicCode());
            rewardSummary.setAuthor(updateContributionCommand.getAuthor());
            rewardSummary.setPublisher(updateContributionCommand.getPublisher());
            rewardSummary.setDuration(updateContributionCommand.getDuration());
            rewardSummary.setResourceUrl(updateContributionCommand.getResourceUrl());
            rewardSummary.setSummary(updateContributionCommand.getSummary());
            rewardSummary.setDescription(updateContributionCommand.getDescription());
            rewardSummary.setModules(updateContributionCommand.getModules());
            rewardSummary.setPrizingModel(updateContributionCommand.getPrizingModel());
            rewardSummary.setContributionType(updateContributionCommand.getContributionType());
            rewardSummary.setResourceType(updateContributionCommand.getResourceType());
            rewardSummary.setPromotion(updateContributionCommand.getPromotion());
            rewardSummary.setImageUrl(updateContributionCommand.getImageUrl());
            rewardSummary.setUpdatedOn(updateContributionCommand.getExecOn());
            rewardSummary.setUpdatedBy(updateContributionCommand.getExecBy().getUserId());
            rewardSummary.setRecommendations(updateContributionCommand.getRecommendations());
            rewardRepository.save(rewardSummary);
        }
    }

    @Override
    public void recommend(ClaimRewardsCommand claimRewardsCommand) {
        log.info(String.format("Entering recommend rewardSummary service -  RewardSummary Id:%s", claimRewardsCommand.getCourseId()));

        RewardSummary rewardSummary = rewardRepository.findBy(claimRewardsCommand.getCourseId());
        if (rewardSummary != null) {
            Integer recommendationsCount = rewardSummary.getRecommendations();
            recommendationsCount += 1;
            rewardSummary.setRecommendations(recommendationsCount);

            rewardSummary.setUpdatedOn(claimRewardsCommand.getExecOn());
            rewardSummary.setUpdatedBy(claimRewardsCommand.getExecBy().getUserId());

            rewardRepository.save(rewardSummary);
        }
    }

   /* @Override
    public void disable(DisableCommand disableCommand) {

        log.info(String.format("Entering disable topic service - Module Code:%s", disableCommand.getCode()));

        rewardRepository.findBy(disableCommand.getCode());
        Module topic = rewardRepository.findBy(disableCommand.getCode());
        if (topic != null) {
            topic.setStatus("INACTIVE");
            topic.setUpdatedOn(disableCommand.getExecOn());
            topic.setUpdatedBy(disableCommand.getExecBy().getUserId());
            rewardRepository.save(topic);
        }
    }*/

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
