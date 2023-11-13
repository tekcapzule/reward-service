package com.tekcapzule.reward.domain.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.core.domain.AggregateRoot;
import com.tekcapzule.core.domain.BaseDomainEntity;
import lombok.*;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@DynamoDBTable(tableName = "Reward")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reward extends BaseDomainEntity implements AggregateRoot {
    @DynamoDBHashKey(attributeName="userId")
    private String userId;
    @DynamoDBRangeKey(attributeName="lifetimeRewardPoints")
    private String lifetimeRewardPoints;
    @DynamoDBAttribute(attributeName = "userName")
    private String userName;
    @DynamoDBAttribute(attributeName = "currentBalance")
    private Integer rewardPoints;
    @DynamoDBAttribute(attributeName = "claims")
    private List<Claim> claims;
    @DynamoDBAttribute(attributeName = "status")
    @DynamoDBTypeConvertedEnum
    private Status status;
}

