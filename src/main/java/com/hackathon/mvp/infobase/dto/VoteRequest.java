package com.hackathon.mvp.infobase.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Setter
@Getter
public class VoteRequest {
    private Long userId;
    private Long votingId;
    private String action;
}