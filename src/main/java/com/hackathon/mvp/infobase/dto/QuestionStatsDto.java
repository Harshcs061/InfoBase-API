package com.hackathon.mvp.infobase.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionStatsDto {
    private int votes;
    private int answers;
    private int views;
}
