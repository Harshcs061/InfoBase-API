package com.hackathon.mvp.infobase.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateQuestionResponseDto {
    private boolean success;
    private String message;
    private QuestionDetailForCreateDto question;
}