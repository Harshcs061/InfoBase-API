package com.hackathon.mvp.infobase.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Builder
public class QuestionDetailForCreateDto {

    private Long id;
    private String title;
    private String description;
    private List<String> tags;
    private String visibility;
    private AuthorDto askedBy;
    private OffsetDateTime createdAt;
}