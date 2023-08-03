package com.tekcapsule.communityrewards.domain.service;

import com.tekcapsule.communityrewards.domain.model.Course;
import com.tekcapsule.communityrewards.domain.command.CreateCommand;
import com.tekcapsule.communityrewards.domain.command.RecommendCommand;
import com.tekcapsule.communityrewards.domain.command.UpdateCommand;

import java.util.List;


public interface CourseService {

    void create(CreateCommand createCommand);

    void update(UpdateCommand updateCommand);

    List<Course> findAll();

    List<Course> findAllByTopicCode(String code);
    void recommend(RecommendCommand recommendCommand);
}
