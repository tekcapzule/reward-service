package com.tekcapsule.communityrewards.domain.repository;

import com.tekcapsule.communityrewards.domain.model.Course;
import com.tekcapsule.core.domain.CrudRepository;

import java.util.List;

public interface CourseDynamoRepository extends CrudRepository<Course, String> {

    List<Course> findAllByTopicCode(String topicCode);
}
