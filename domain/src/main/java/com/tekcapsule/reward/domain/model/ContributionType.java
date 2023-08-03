package com.tekcapsule.reward.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ContributionType {
    CODE("Code Contribution"),
    CONTENT("Content Preparation"),
    EVENT("Community Event Contribution");

    @Getter
    private String value;
}