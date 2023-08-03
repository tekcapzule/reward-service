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
public class Contribution extends BaseDomainEntity implements AggregateRoot {
    @DynamoDBHashKey(attributeName = "contributionId")
    private String contributionId;
    @DynamoDBAttribute(attributeName = "userId")
    private String userId;
    @DynamoDBAttribute(attributeName = "title")
    private String title;
    @DynamoDBAttribute(attributeName = "resourceUrl")
    private String resourceUrl;
    @DynamoDBAttribute(attributeName = "resourceType")
    @DynamoDBTypeConvertedEnum
    private ResourceType resourceType;
    @DynamoDBAttribute(attributeName = "topicCode")
    private String topicCode;
    @DynamoDBAttribute(attributeName = "comment")
    private String comment;
    @DynamoDBAttribute(attributeName = "contributionType")
    @DynamoDBTypeConvertedEnum
    private ContributionType contributionType;
    @DynamoDBAttribute(attributeName = "status")
    @DynamoDBTypeConvertedEnum
    private Status status;
}

