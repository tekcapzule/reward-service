package com.tekcapsule.reward.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.Command;
import com.tekcapsule.reward.domain.model.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UpdateContributionCommand extends Command {
    private String contributionId;
    private String userId;
    private String title;
    private String resourceUrl;
    private ResourceType resourceType;
    private String topicCode;
    private String comment;
    private ContributionType contributionType;

}
