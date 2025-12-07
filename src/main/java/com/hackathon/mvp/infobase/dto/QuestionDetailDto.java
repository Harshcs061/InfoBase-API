package com.hackathon.mvp.infobase.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Builder
public class QuestionDetailDto {
    private String id;
    private String title;
    private String description;   // maps from entity.description
    private List<String> tags;
    private AskedByDto askedBy;
    private int votes;
    private int answer_count;
    private int views;
    private String related_project;
    private LocalDateTime createdAt;
}