package com.tekcapsule.reward.domain.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.AggregateRoot;
import com.tekcapsule.core.domain.BaseDomainEntity;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@DynamoDBTable(tableName = "Reward")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RewardSummary extends BaseDomainEntity implements AggregateRoot {
    @DynamoDBHashKey(attributeName="rewardId")
    private String rewardId;
    @DynamoDBAttribute(attributeName = "userId")
    private String userId;
    @DynamoDBAttribute(attributeName = "userName")
    private String userName;
    @DynamoDBAttribute(attributeName = "rewardPoints")
    private String rewardPoints;
    @DynamoDBAttribute(attributeName = "claimedPoints")
    private String claimedPoints;
    @DynamoDBAttribute(attributeName = "lastClaimOn")
    private String summary;
    @DynamoDBAttribute(attributeName = "status")
    @DynamoDBTypeConvertedEnum
    private Status status;
}

