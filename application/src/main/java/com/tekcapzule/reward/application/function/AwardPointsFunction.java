package com.tekcapzule.reward.application.function;

import com.tekcapzule.core.domain.Origin;
import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.PayloadUtil;
import com.tekcapzule.core.utils.Stage;
import com.tekcapzule.reward.application.config.AppConfig;
import com.tekcapzule.reward.application.function.input.AwardPointsInput;
import com.tekcapzule.reward.application.mapper.InputOutputMapper;
import com.tekcapzule.reward.domain.command.AwardPointsCommand;
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