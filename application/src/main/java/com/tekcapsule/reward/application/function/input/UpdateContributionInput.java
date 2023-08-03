package com.tekcapsule.reward.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.reward.domain.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class UpdateContributionInput {
    private String contributionId;
    private String userId;
    private String title;
    private String resourceUrl;
    private ResourceType resourceType;
    private String topicCode;
    private String comment;
    private ContributionType contributionType;
}
