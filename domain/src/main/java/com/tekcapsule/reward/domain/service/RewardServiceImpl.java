package com.tekcapsule.reward.domain.service;

import com.tekcapsule.reward.domain.model.RewardSummary;
import com.tekcapsule.reward.domain.model.Status;
import com.tekcapsule.reward.domain.repository.RewardDynamoRepository;
import com.tekcapsule.reward.domain.command.CreateCommand;
import com.tekcapsule.reward.domain.command.RecommendCommand;
import com.tekcapsule.reward.domain.command.UpdateCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class RewardServiceImpl implements RewardService {
    private RewardDynamoRepository rewardDynamoRepository;

    @Autowired
    public RewardServiceImpl(RewardDynamoRepository rewardDynamoRepository) {
        this.rewardDynamoRepository = rewardDynamoRepository;
    }

    @Override
    public void create(CreateCommand createCommand) {

        log.info(String.format("Entering create rewardSummary service - Module Code :%s", createCommand.getTopicCode()));

        RewardSummary rewardSummary = RewardSummary.builder()
                .title(createCommand.getTitle())
                .topicCode(createCommand.getTopicCode())
                .author(createCommand.getAuthor())
                .publisher(createCommand.getPublisher())
                .duration(createCommand.getDuration())
                .resourceUrl(createCommand.getResourceUrl())
                .summary(createCommand.getSummary())
                .description(createCommand.getDescription())
                .modules(createCommand.getModules())
                .prizingModel(createCommand.getPrizingModel())
                .contributionType(createCommand.getContributionType())
                .resourceType(createCommand.getResourceType())
                .imageUrl(createCommand.getImageUrl())
                .promotion(createCommand.getPromotion())
                .status(Status.ACTIVE)
                .recommendations(createCommand.getRecommendations())
                .build();

        rewardSummary.setAddedOn(createCommand.getExecOn());
        rewardSummary.setAddedBy(createCommand.getExecBy().getUserId());

        rewardDynamoRepository.save(rewardSummary);
    }

    @Override
    public void update(UpdateCommand updateCommand) {

        log.info(String.format("Entering update rewardSummary service - RewardSummary ID:%s", updateCommand.getCourseId()));

        RewardSummary rewardSummary = rewardDynamoRepository.findBy(updateCommand.getCourseId());
        if (rewardSummary != null) {
            rewardSummary.setTitle(updateCommand.getTitle());
            rewardSummary.setTopicCode(updateCommand.getTopicCode());
            rewardSummary.setAuthor(updateCommand.getAuthor());
            rewardSummary.setPublisher(updateCommand.getPublisher());
            rewardSummary.setDuration(updateCommand.getDuration());
            rewardSummary.setResourceUrl(updateCommand.getResourceUrl());
            rewardSummary.setSummary(updateCommand.getSummary());
            rewardSummary.setDescription(updateCommand.getDescription());
            rewardSummary.setModules(updateCommand.getModules());
            rewardSummary.setPrizingModel(updateCommand.getPrizingModel());
            rewardSummary.setContributionType(updateCommand.getContributionType());
            rewardSummary.setResourceType(updateCommand.getResourceType());
            rewardSummary.setPromotion(updateCommand.getPromotion());
            rewardSummary.setImageUrl(updateCommand.getImageUrl());
            rewardSummary.setUpdatedOn(updateCommand.getExecOn());
            rewardSummary.setUpdatedBy(updateCommand.getExecBy().getUserId());
            rewardSummary.setRecommendations(updateCommand.getRecommendations());
            rewardDynamoRepository.save(rewardSummary);
        }
    }

    @Override
    public void recommend(RecommendCommand recommendCommand) {
        log.info(String.format("Entering recommend rewardSummary service -  RewardSummary Id:%s", recommendCommand.getCourseId()));

        RewardSummary rewardSummary = rewardDynamoRepository.findBy(recommendCommand.getCourseId());
        if (rewardSummary != null) {
            Integer recommendationsCount = rewardSummary.getRecommendations();
            recommendationsCount += 1;
            rewardSummary.setRecommendations(recommendationsCount);

            rewardSummary.setUpdatedOn(recommendCommand.getExecOn());
            rewardSummary.setUpdatedBy(recommendCommand.getExecBy().getUserId());

            rewardDynamoRepository.save(rewardSummary);
        }
    }

   /* @Override
    public void disable(DisableCommand disableCommand) {

        log.info(String.format("Entering disable topic service - Module Code:%s", disableCommand.getCode()));

        rewardDynamoRepository.findBy(disableCommand.getCode());
        Module topic = rewardDynamoRepository.findBy(disableCommand.getCode());
        if (topic != null) {
            topic.setStatus("INACTIVE");
            topic.setUpdatedOn(disableCommand.getExecOn());
            topic.setUpdatedBy(disableCommand.getExecBy().getUserId());
            rewardDynamoRepository.save(topic);
        }
    }*/

    @Override
    public List<RewardSummary> findAll() {

        log.info("Entering findAll RewardSummary service");

        return rewardDynamoRepository.findAll();
    }

    @Override
    public List<RewardSummary> findAllByTopicCode(String topicCode) {

        log.info(String.format("Entering findAllByTopicCode RewardSummary service - Module code:%s", topicCode));

        return rewardDynamoRepository.findAllByTopicCode(topicCode);
    }


}
