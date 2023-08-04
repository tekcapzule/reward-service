package com.tekcapsule.reward.domain.service;

import com.tekcapsule.reward.domain.model.Contribution;
import com.tekcapsule.reward.domain.model.Status;
import com.tekcapsule.reward.domain.repository.ContributionRepository;
import com.tekcapsule.reward.domain.command.CreateContributionCommand;
import com.tekcapsule.reward.domain.command.UpdateContributionCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ContributionServiceImpl implements ContributionService {
    private final ContributionRepository contributionRepository;

    @Autowired
    public ContributionServiceImpl(ContributionRepository contributionRepository) {
        this.contributionRepository = contributionRepository;
    }

    @Override
    public void create(CreateContributionCommand createContributionCommand) {

        log.info(String.format("Entering create rewardSummary service - Module Code :%s", createContributionCommand.getTopicCode()));
        Contribution contribution = Contribution.builder()
                .topicCode(createContributionCommand.getTopicCode())
                .resourceUrl(createContributionCommand.getResourceUrl())
                .contributionType(createContributionCommand.getContributionType())
                .resourceType(createContributionCommand.getResourceType())
                .status(Status.ACTIVE)
                .build();

        contribution.setAddedOn(createContributionCommand.getExecOn());
        contribution.setAddedBy(createContributionCommand.getExecBy().getUserId());

        contributionRepository.save(contribution);
    }

    @Override
    public void update(UpdateContributionCommand updateContributionCommand) {

        log.info(String.format("Entering update rewardSummary service - RewardSummary ID:%s", updateContributionCommand.getContributionId()));

        Contribution contribution = contributionRepository.findBy(updateContributionCommand.getContributionId());
        if (contribution != null) {
            contribution.setTitle(updateContributionCommand.getTitle());
            contribution.setTopicCode(updateContributionCommand.getTopicCode());
            contribution.setUpdatedOn(updateContributionCommand.getExecOn());
            contribution.setUpdatedBy(updateContributionCommand.getExecBy().getUserId());
            contributionRepository.save(contribution);
        }
    }

    @Override
    public List<Contribution> findAll() {
        log.info("Entering findAll RewardSummary service");
        return contributionRepository.findAll();
    }

    @Override
    public List<Contribution> findAllByUserId(String userId) {

        log.info(String.format("Entering findAllByTopicCode RewardSummary service - Module code:%s", userId));

        return null;
    }
}
