package com.tekcapsule.reward.application.function;

import com.tekcapsule.reward.application.config.AppConfig;
import com.tekcapsule.reward.application.function.input.ApproveContributionInput;
import com.tekcapsule.reward.application.mapper.InputOutputMapper;
import com.tekcapsule.reward.domain.command.ClaimRewardsCommand;
import com.tekcapsule.reward.domain.service.RewardService;
import com.tekcapsule.core.domain.Origin;
import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.core.utils.Outcome;
import com.tekcapsule.core.utils.PayloadUtil;
import com.tekcapsule.core.utils.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
@Slf4j
public class RecommendFunction implements Function<Message<ApproveContributionInput>, Message<Void>> {

    private final RewardService rewardService;

    private final AppConfig appConfig;

    public RecommendFunction(final RewardService rewardService, final AppConfig appConfig) {
        this.rewardService = rewardService;
        this.appConfig = appConfig;
    }


    @Override
    public Message<Void> apply(Message<ApproveContributionInput> recommendInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            ApproveContributionInput approveContributionInput = recommendInputMessage.getPayload();
            log.info(String.format("Entering recommend course Function -  RewardSummary Id:%s", approveContributionInput.getCourseId()));
            Origin origin = HeaderUtil.buildOriginFromHeaders(recommendInputMessage.getHeaders());
            ClaimRewardsCommand claimRewardsCommand = InputOutputMapper.buildRecommendCommandFromRecommendInput.apply(approveContributionInput, origin);
            rewardService.recommend(claimRewardsCommand);
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