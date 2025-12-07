package com.hackathon.mvp.infobase.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AnswerDto {
    private String id;
    private Long questionId;
    private AuthorDto author;
    private String body;
    private int upvotes;
    private int downvotes;
    private int score;
    private boolean isAccepted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isEdited;
}