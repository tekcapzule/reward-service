package com.tekcapsule.reward.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.reward.domain.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class CreateContributionInput {
    private String title;
    private String topicCode;
    private String author;
    private String publisher;
    private String duration;
    private String resourceUrl;
    private String summary;
    private String description;
    private List<Module> modules;
    private PrizingModel prizingModel;
    private ContributionType contributionType;
    private ResourceType resourceType;
    private String imageUrl;

    private int recommendations;

    private Promotion promotion;

}