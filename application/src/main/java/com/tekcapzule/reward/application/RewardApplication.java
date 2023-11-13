package com.tekcapzule.reward.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.tekcapzule.reward","com.tekcapzule.core"})
public class RewardApplication {
    public static void main(String[] args) {
        SpringApplication.run(RewardApplication.class, args);
    }
}
