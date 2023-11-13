package com.tekcapzule.reward.application.function;

import com.tekcapzule.core.domain.Origin;
import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.PayloadUtil;
import com.tekcapzule.core.utils.Stage;
import com.tekcapzule.reward.application.config.AppConfig;
import com.tekcapzule.reward.application.function.input.GetPointsInput;
import com.tekcapzule.reward.domain.model.Reward;
import com.tekcapzule.reward.domain.service.RewardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class GetPointsFunction implements Function<Message<GetPointsInput>, Message<Reward>> {

    private final RewardService rewardService;

    private final AppConfig appConfig;

    public GetPointsFunction(final RewardService rewardService, final AppConfig appConfig) {
        this.rewardService = rewardService;
        this.appConfig = appConfig;
    }

    @Override
    public Message<Reward> apply(Message<GetPointsInput> getPointsInputMessage) {

        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        String stage = appConfig.getStage().toUpperCase();

        try {
            GetPointsInput getPointsInput = getPointsInputMessage.getPayload();
            log.info(String.format("Entering get points Function - userId :%s", getPointsInput.getUserId()));
            Origin origin = HeaderUtil.buildOriginFromHeaders(getPointsInputMessage.getHeaders());
            rewardService.findByUserId(getPointsInput.getUserId());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
            payload = PayloadUtil.composePayload(Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
            payload = PayloadUtil.composePayload(Outcome.ERROR);
        }
        return new GenericMessage(payload, responseHeaders);
    }
}