package com.tekcapzule.reward.application.function;

import com.tekcapzule.reward.application.config.AppConfig;
import com.tekcapzule.core.domain.Origin;
import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.PayloadUtil;
import com.tekcapzule.core.utils.Stage;
import com.tekcapzule.reward.application.function.input.RedeemPointsInput;
import com.tekcapzule.reward.application.mapper.InputOutputMapper;
import com.tekcapzule.reward.domain.command.RedeemPointsCommand;
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