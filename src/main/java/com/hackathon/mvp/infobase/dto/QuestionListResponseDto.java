package com.hackathon.mvp.infobase.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QuestionListResponseDto {
    private long total;
    private int page;
    private int limit;
    private List<QuestionListItemDto> questions;
}