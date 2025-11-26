package com.hackathon.mvp.infobase.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Builder
public class QuestionDetailDto {
    private String id;
    private String title;
    private String body;   // maps from entity.description
    private List<String> tags;
    private AskedByDto askedBy;
    private QuestionStatsDto stats;
    private OffsetDateTime createdAt;
}