package com.tekcapsule.reward.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Status {
    SUBMITTED("Submitted"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    CLAIMED("Claimed"),
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    EXPIRED("Expired");

    @Getter
    private String value;
}