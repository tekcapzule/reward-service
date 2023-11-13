package com.tekcapzule.reward.domain.command;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.core.domain.Command;
import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class RedeemPointsCommand extends Command {
    private String userId;
    private Integer points;
    private String comment;
}