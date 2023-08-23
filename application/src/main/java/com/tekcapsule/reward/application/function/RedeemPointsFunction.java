package com.tekcapsule.reward.application.function;

import com.tekcapsule.reward.application.config.AppConfig;
import com.tekcapsule.core.domain.Origin;
import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.core.utils.Outcome;
import com.tekcapsule.core.utils.PayloadUtil;
import com.tekcapsule.core.utils.Stage;
import com.tekcapsule.reward.application.function.input.RedeemPointsInput;
import com.tekcapsule.reward.application.mapper.InputOutputMapper;
import com.tekcapsule.reward.domain.command.RedeemPointsCommand;
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
public class RedeemPointsFunction implements Function<Message<RedeemPointsInput>, Message<Void>> {

    private final RewardService rewardService;

    private final AppConfig appConfig;

    public RedeemPointsFunction(final RewardService rewardService, final AppConfig appConfig) {
        this.rewardService = rewardService;
        this.appConfig = appConfig;
    }

    @Override
    public Message<Void> apply(Message<RedeemPointsInput> redeemPointsInputMessage) {

        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        String stage = appConfig.getStage().toUpperCase();

        try {
            RedeemPointsInput redeemPointsInput = redeemPointsInputMessage.getPayload();
            log.info(String.format("Entering redeem points Function - UserId :%s", redeemPointsInput.getUserId()));
            Origin origin = HeaderUtil.buildOriginFromHeaders(redeemPointsInputMessage.getHeaders());
            RedeemPointsCommand redeemPointsCommand = InputOutputMapper.buildRedeemPointsCommandFromRedeemPointsInput.apply(redeemPointsInput, origin);
            rewardService.redeem(redeemPointsCommand);
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