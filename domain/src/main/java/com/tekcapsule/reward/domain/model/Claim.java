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
public class Claim extends BaseDomainEntity implements AggregateRoot {
    @DynamoDBHashKey(attributeName="claimId")
    private String claimId;
    @DynamoDBAttribute(attributeName = "userId")
    private String userId;
    @DynamoDBAttribute(attributeName = "claimedPoints")
    private String claimedPoints;
    @DynamoDBAttribute(attributeName = "claimedOn")
    private String claimedOn;
    @DynamoDBAttribute(attributeName = "comment")
    private String comment;
    @DynamoDBAttribute(attributeName = "status")
    @DynamoDBTypeConvertedEnum
    private Status status;
}

