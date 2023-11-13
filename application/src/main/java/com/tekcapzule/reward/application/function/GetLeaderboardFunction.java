package com.tekcapzule.reward.application.function;

import com.tekcapzule.reward.application.config.AppConfig;
import com.tekcapzule.core.domain.EmptyFunctionInput;
import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.Stage;
import com.tekcapzule.reward.domain.model.Reward;
import com.tekcapzule.reward.domain.service.RewardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
@Slf4j
public class GetLeaderboardFunction implements Function<Message<EmptyFunctionInput>, Message<List<Reward>>> {

    private final RewardService rewardService;

    private final AppConfig appConfig;

    public GetLeaderboardFunction(final RewardService rewardService, final AppConfig appConfig) {
        this.rewardService = rewardService;
        this.appConfig = appConfig;
    }


    @Override
    public Message<List<Reward>> apply(Message<EmptyFunctionInput> getAllInputMessage) {

        Map<String, Object> responseHeaders = new HashMap<>();
        List<Reward> rewards = new ArrayList<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            log.info("Entering get leaderboard Function");
            rewards = rewardService.getLeaderboard();
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage<>(rewards, responseHeaders);
    }
}