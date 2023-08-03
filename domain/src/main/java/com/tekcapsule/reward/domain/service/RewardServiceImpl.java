package com.tekcapsule.reward.domain.service;

import com.tekcapsule.reward.domain.model.Course;
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

        log.info(String.format("Entering create course service - Module Code :%s", createCommand.getTopicCode()));

        Course course = Course.builder()
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
                .deliveryMode(createCommand.getDeliveryMode())
                .learningMode(createCommand.getLearningMode())
                .imageUrl(createCommand.getImageUrl())
                .promotion(createCommand.getPromotion())
                .status(Status.ACTIVE)
                .recommendations(createCommand.getRecommendations())
                .build();

        course.setAddedOn(createCommand.getExecOn());
        course.setAddedBy(createCommand.getExecBy().getUserId());

        rewardDynamoRepository.save(course);
    }

    @Override
    public void update(UpdateCommand updateCommand) {

        log.info(String.format("Entering update course service - Course ID:%s", updateCommand.getCourseId()));

        Course course = rewardDynamoRepository.findBy(updateCommand.getCourseId());
        if (course != null) {
            course.setTitle(updateCommand.getTitle());
            course.setTopicCode(updateCommand.getTopicCode());
            course.setAuthor(updateCommand.getAuthor());
            course.setPublisher(updateCommand.getPublisher());
            course.setDuration(updateCommand.getDuration());
            course.setResourceUrl(updateCommand.getResourceUrl());
            course.setSummary(updateCommand.getSummary());
            course.setDescription(updateCommand.getDescription());
            course.setModules(updateCommand.getModules());
            course.setPrizingModel(updateCommand.getPrizingModel());
            course.setDeliveryMode(updateCommand.getDeliveryMode());
            course.setLearningMode(updateCommand.getLearningMode());
            course.setPromotion(updateCommand.getPromotion());
            course.setImageUrl(updateCommand.getImageUrl());
            course.setUpdatedOn(updateCommand.getExecOn());
            course.setUpdatedBy(updateCommand.getExecBy().getUserId());
            course.setRecommendations(updateCommand.getRecommendations());
            rewardDynamoRepository.save(course);
        }
    }

    @Override
    public void recommend(RecommendCommand recommendCommand) {
        log.info(String.format("Entering recommend course service -  Course Id:%s", recommendCommand.getCourseId()));

        Course course = rewardDynamoRepository.findBy(recommendCommand.getCourseId());
        if (course != null) {
            Integer recommendationsCount = course.getRecommendations();
            recommendationsCount += 1;
            course.setRecommendations(recommendationsCount);

            course.setUpdatedOn(recommendCommand.getExecOn());
            course.setUpdatedBy(recommendCommand.getExecBy().getUserId());

            rewardDynamoRepository.save(course);
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
    public List<Course> findAll() {

        log.info("Entering findAll Course service");

        return rewardDynamoRepository.findAll();
    }

    @Override
    public List<Course> findAllByTopicCode(String topicCode) {

        log.info(String.format("Entering findAllByTopicCode Course service - Module code:%s", topicCode));

        return rewardDynamoRepository.findAllByTopicCode(topicCode);
    }


}
