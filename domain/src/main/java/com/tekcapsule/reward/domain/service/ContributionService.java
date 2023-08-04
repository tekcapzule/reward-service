package com.tekcapsule.reward.domain.service;

import com.tekcapsule.reward.domain.command.CreateContributionCommand;
import com.tekcapsule.reward.domain.command.UpdateContributionCommand;
import com.tekcapsule.reward.domain.model.Contribution;

import java.util.List;

public interface ContributionService {
    void create(CreateContributionCommand createContributionCommand);
    void update(UpdateContributionCommand updateContributionCommand);

    List<Contribution> findAll();

    List<Contribution> findAllByUserId(String userId);
}
