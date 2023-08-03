package com.tekcapsule.reward.domain.command;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.Command;
import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class ClaimRewardsCommand extends Command {
    private String userId;
    private int rewardPoints;
}