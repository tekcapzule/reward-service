package com.tekcapsule.reward.domain.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.tekcapsule.reward.domain.model.RewardSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Repository
public class RewardDynamoRepositoryImpl implements RewardRepository {

    private DynamoDBMapper dynamo;
    public static final String ACTIVE_STATUS = "ACTIVE";

    @Autowired
    public RewardDynamoRepositoryImpl(DynamoDBMapper dynamo) {
        this.dynamo = dynamo;
    }

    @Override
    public List<RewardSummary> findAll() {

        return dynamo.scan(RewardSummary.class,new DynamoDBScanExpression());
    }

    @Override
    public List<RewardSummary> findAllByTopicCode(String topicCode) {

        HashMap<String, AttributeValue> expAttributes = new HashMap<>();
        expAttributes.put(":status", new AttributeValue().withS(ACTIVE_STATUS));
        expAttributes.put(":topicCode", new AttributeValue().withS(topicCode));

        HashMap<String, String> expNames = new HashMap<>();
        expNames.put("#status", "status");
        expNames.put("#topicCode", "topicCode");


        DynamoDBQueryExpression<RewardSummary> queryExpression = new DynamoDBQueryExpression<RewardSummary>()
                .withIndexName("topicGSI").withConsistentRead(false)
                .withKeyConditionExpression("#status = :status and #topicCode = :topicCode")
                .withExpressionAttributeValues(expAttributes)
                .withExpressionAttributeNames(expNames);

        return dynamo.query(RewardSummary.class, queryExpression);

    }

    @Override
    public RewardSummary findBy(String code) {
        return dynamo.load(RewardSummary.class, code);
    }

    @Override
    public RewardSummary save(RewardSummary rewardSummary) {
        dynamo.save(rewardSummary);
        return rewardSummary;
    }
}
