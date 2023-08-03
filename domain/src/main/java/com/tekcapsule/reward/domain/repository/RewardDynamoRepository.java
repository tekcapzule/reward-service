package com.tekcapsule.reward.domain.repository;

import com.tekcapsule.reward.domain.model.Course;
import com.tekcapsule.core.domain.CrudRepository;

import java.util.List;

public interface RewardDynamoRepository extends CrudRepository<Course, String> {

    List<Course> findAllByTopicCode(String topicCode);
}
