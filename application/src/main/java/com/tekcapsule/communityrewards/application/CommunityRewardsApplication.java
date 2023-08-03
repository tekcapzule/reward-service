package com.tekcapsule.communityrewards.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.tekcapsule.communityrewards","com.tekcapsule.core"})
public class CommunityRewardsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommunityRewardsApplication.class, args);
    }
}
