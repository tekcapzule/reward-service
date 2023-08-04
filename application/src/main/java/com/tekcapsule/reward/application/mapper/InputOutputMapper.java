package com.tekcapsule.reward.application.mapper;

import com.tekcapsule.reward.application.function.input.*;
import com.tekcapsule.core.domain.Command;
import com.tekcapsule.core.domain.ExecBy;
import com.tekcapsule.core.domain.Origin;
import com.tekcapsule.reward.domain.command.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.function.BiFunction;

@Slf4j
public final class InputOutputMapper {
    private InputOutputMapper() {

    }

    public static final BiFunction<Command, Origin, Command> addOrigin = (command, origin) -> {
        OffsetDateTime utc = OffsetDateTime.now(ZoneOffset.UTC);
        command.setChannel(origin.getChannel());
        command.setExecBy(ExecBy.builder().tenantId(origin.getTenantId()).userId(origin.getUserId()).build());
        command.setExecOn(utc.toString());
        return command;
    };
    public static final BiFunction<CreateContributionInput, Origin, CreateContributionCommand> buildCreateContributionCommandFromCreateContributionInput = (createContributionInput, origin) -> {
        CreateContributionCommand createContributionCommand = CreateContributionCommand.builder().build();
        BeanUtils.copyProperties(createContributionInput, createContributionCommand);
        addOrigin.apply(createContributionCommand, origin);
        return createContributionCommand;
    };

    public static final BiFunction<UpdateContributionInput, Origin, UpdateContributionCommand> buildUpdateContributionCommandFromUpdateContributionInput = (updateContributionInput, origin) -> {
        UpdateContributionCommand updateContributionCommand = UpdateContributionCommand.builder().build();
        BeanUtils.copyProperties(updateContributionInput, updateContributionCommand);
        addOrigin.apply(updateContributionCommand, origin);
        return updateContributionCommand;
    };

    public static final BiFunction<ApproveContributionInput, Origin, ApproveContributionCommand> buildApproveContributionCommandFromApproveContributionInput = (approveContributionInput, origin) -> {
        ApproveContributionCommand approveContributionCommand =  ApproveContributionCommand.builder().build();
        BeanUtils.copyProperties(approveContributionInput, approveContributionCommand);
        addOrigin.apply(approveContributionCommand, origin);
        return approveContributionCommand;
    };
    public static final BiFunction<RejectContributionInput, Origin, RejectContributionCommand> buildRejectContributionCommandFromRejectContributionInput = (rejectContributionInput, origin) -> {
        RejectContributionCommand rejectContributionCommand =  RejectContributionCommand.builder().build();
        BeanUtils.copyProperties(rejectContributionInput, rejectContributionCommand);
        addOrigin.apply(rejectContributionCommand, origin);
        return rejectContributionCommand;
    };

    public static final BiFunction<ClaimRewardsInput, Origin, ClaimRewardsCommand> buildClaimRewardsCommandFromClaimRewardsInput = (claimRewardsInput, origin) -> {
        ClaimRewardsCommand claimRewardsCommand =  ClaimRewardsCommand.builder().build();
        BeanUtils.copyProperties(claimRewardsInput, claimRewardsCommand);
        addOrigin.apply(claimRewardsCommand, origin);
        return claimRewardsCommand;
    };
}
