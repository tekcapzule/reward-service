package com.tekcapsule.reward.application.function;

import com.tekcapsule.reward.application.config.AppConfig;
import com.tekcapsule.core.domain.Origin;
import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.core.utils.Outcome;
import com.tekcapsule.core.utils.PayloadUtil;
import com.tekcapsule.core.utils.Stage;
import com.tekcapsule.reward.application.function.input.UpdateContributionInput;
import com.tekcapsule.reward.application.mapper.InputOutputMapper;
import com.tekcapsule.reward.domain.command.UpdateContributionCommand;
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
public class UpdateContributionFunction implements Function<Message<UpdateContributionInput>, Message<Void>> {

    private final RewardService rewardService;

    private final AppConfig appConfig;

    public UpdateContributionFunction(final RewardService rewardService, final AppConfig appConfig) {
        this.rewardService = rewardService;
        this.appConfig = appConfig;
    }

    @Override
    public Message<Void> apply(Message<UpdateContributionInput> updateInputMessage) {

        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        String stage = appConfig.getStage().toUpperCase();

        try {
            UpdateContributionInput updateContributionInput = updateInputMessage.getPayload();
            log.info(String.format("Entering update course Function - Module Code:%s", updateContributionInput.getTopicCode()));
            Origin origin = HeaderUtil.buildOriginFromHeaders(updateInputMessage.getHeaders());
            UpdateContributionCommand updateContributionCommand = InputOutputMapper.buildUpdateContributionCommandFromUpdateContributionInput.apply(updateContributionInput, origin);
            rewardService.update(updateContributionCommand);
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