package com.tekcapsule.reward.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.reward.domain.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class CreateContributionInput {
    private String userId;
    private String title;
    private String resourceUrl;
    private ResourceType resourceType;
    private String topicCode;
    private String comment;
    private ContributionType contributionType;
}