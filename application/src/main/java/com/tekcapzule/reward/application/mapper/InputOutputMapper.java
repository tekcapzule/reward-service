package com.tekcapzule.reward.application.mapper;

import com.tekcapzule.reward.application.function.input.*;
import com.tekcapzule.core.domain.Command;
import com.tekcapzule.core.domain.ExecBy;
import com.tekcapzule.core.domain.Origin;
import com.tekcapzule.reward.application.function.input.AwardPointsInput;
import com.tekcapzule.reward.application.function.input.RedeemPointsInput;
import com.tekcapzule.reward.domain.command.AwardPointsCommand;
import com.tekcapzule.reward.domain.command.RedeemPointsCommand;
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
    public static final BiFunction<AwardPointsInput, Origin, AwardPointsCommand> buildAwardPointsCommandFromAwardPointsInput = (awardPointsInput, origin) -> {
        AwardPointsCommand awardPointsCommand = AwardPointsCommand.builder().build();
        BeanUtils.copyProperties(awardPointsInput, awardPointsCommand);
        addOrigin.apply(awardPointsCommand, origin);
        return awardPointsCommand;
    };

    public static final BiFunction<RedeemPointsInput, Origin, RedeemPointsCommand> buildRedeemPointsCommandFromRedeemPointsInput = (redeemPointsInput, origin) -> {
        RedeemPointsCommand redeemPointsCommand = RedeemPointsCommand.builder().build();
        BeanUtils.copyProperties(redeemPointsCommand, redeemPointsCommand);
        addOrigin.apply(redeemPointsCommand, origin);
        return redeemPointsCommand;
    };
}
