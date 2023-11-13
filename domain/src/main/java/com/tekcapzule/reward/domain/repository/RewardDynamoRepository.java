package com.tekcapzule.reward.domain.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tekcapzule.reward.domain.model.Reward;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class RewardDynamoRepository implements RewardRepository {

    private DynamoDBMapper dynamo;
    public static final String ACTIVE_STATUS = "ACTIVE";

    @Autowired
    public RewardDynamoRepository(DynamoDBMapper dynamo) {
        this.dynamo = dynamo;
    }

    @Override
    public List<Reward> findAll() {

        return dynamo.scan(Reward.class,new DynamoDBScanExpression());
    }

    @Override
    public List<Reward> findTopContributors() {
        return dynamo.scan(Reward.class,new DynamoDBScanExpression());
    }

    @Override
    public Reward findBy(String code) {
        return dynamo.load(Reward.class, code);
    }

    @Override
    public Reward save(Reward reward) {
        dynamo.save(reward);
        return reward;
    }

}
