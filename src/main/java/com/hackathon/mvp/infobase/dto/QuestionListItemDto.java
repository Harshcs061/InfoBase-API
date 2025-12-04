package com.hackathon.mvp.infobase.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Builder
public class QuestionListItemDto {
    private String id;
    private String title;
    private String content; // maps from entity.description
    private List<String> tags;
    private AuthorDto author;
    private int votes;
    private int answersCount;
    private LocalDateTime createdAt;
}
