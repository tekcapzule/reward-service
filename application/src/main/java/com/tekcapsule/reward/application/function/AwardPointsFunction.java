package com.tekcapsule.reward.application.function;

import com.tekcapsule.core.domain.Origin;
import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.core.utils.Outcome;
import com.tekcapsule.core.utils.PayloadUtil;
import com.tekcapsule.core.utils.Stage;
import com.tekcapsule.reward.application.config.AppConfig;
import com.tekcapsule.reward.application.function.input.AwardPointsInput;
import com.tekcapsule.reward.application.mapper.InputOutputMapper;
import com.tekcapsule.reward.domain.command.AwardPointsCommand;
import com.tekcapsule.reward.domain.service.RewardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class AwardPointsFunction implements Function<Message<AwardPointsInput>, Message<Void>> {

    private final RewardService rewardService;

    private final AppConfig appConfig;

    public AwardPointsFunction(final RewardService rewardService, final AppConfig appConfig) {
        this.rewardService = rewardService;
        this.appConfig = appConfig;
    }

    @Override
    public Message<Void> apply(Message<AwardPointsInput> awardPointsInputMessage) {

        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        String stage = appConfig.getStage().toUpperCase();

        try {
            AwardPointsInput awardPointsInput = awardPointsInputMessage.getPayload();
            log.info(String.format("Entering award points Function - UserId:%s", awardPointsInput.getUserId()));
            Origin origin = HeaderUtil.buildOriginFromHeaders(awardPointsInputMessage.getHeaders());
            AwardPointsCommand awardPointsCommand = InputOutputMapper.buildAwardPointsCommandFromAwardPointsInput.apply(awardPointsInput, origin);
            rewardService.award(awardPointsCommand);
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