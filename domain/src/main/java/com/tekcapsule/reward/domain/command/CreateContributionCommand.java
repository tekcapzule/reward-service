package com.tekcapsule.reward.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.Command;
import com.tekcapsule.reward.domain.model.*;
import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class CreateContributionCommand extends Command {
    private String userId;
    private String title;
    private String resourceUrl;
    private ResourceType resourceType;
    private String topicCode;
    private String comment;
    private ContributionType contributionType;
}