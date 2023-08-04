package com.tekcapsule.reward.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class ClaimRewardsInput {
    private String userId;
    private int rewardPoints;
}
