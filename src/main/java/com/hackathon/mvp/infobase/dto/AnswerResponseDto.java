package com.hackathon.mvp.infobase.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AnswerResponseDto {
    private boolean success;
    private AnswerDto data;
}