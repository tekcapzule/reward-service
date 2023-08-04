package com.tekcapsule.reward.application.function;

import com.tekcapsule.reward.application.config.AppConfig;
import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.core.utils.Outcome;
import com.tekcapsule.core.utils.Stage;
import com.tekcapsule.reward.application.function.input.GetContributionsInput;
import com.tekcapsule.reward.domain.model.RewardSummary;
import com.tekcapsule.reward.domain.service.RewardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class GetContributionsFunction implements Function<Message<GetContributionsInput>, Message<List<RewardSummary>>> {

    private final RewardService rewardService;

    private final AppConfig appConfig;

    public GetContributionsFunction(final RewardService rewardService, final AppConfig appConfig) {
        this.rewardService = rewardService;
        this.appConfig = appConfig;
    }


    @Override
    public Message<List<RewardSummary>> apply(Message<GetContributionsInput> getInputMessage) {

        Map<String, Object> responseHeaders = new HashMap<>();
        List<RewardSummary> cours = new ArrayList<>();

        String stage = appConfig.getStage().toUpperCase();

        try {
            GetContributionsInput getContributionsInput = getInputMessage.getPayload();
            log.info(String.format("Entering get course Function -Module Code:%s", getContributionsInput.getTopicCode()));
            cours = rewardService.findAllByTopicCode(getContributionsInput.getTopicCode());
            if (cours.isEmpty()) {
                responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.NOT_FOUND);
            } else {
                responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage<>(cours, responseHeaders);
    }
}