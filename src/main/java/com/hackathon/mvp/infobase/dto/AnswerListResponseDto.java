package com.hackathon.mvp.infobase.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AnswerListResponseDto {
    private boolean success;
    private AnswerDataDto data;

    @Getter
    @Setter
    @Builder
    public static class AnswerDataDto {
        private Long questionId;
        private List<AnswerDto> answers;
        private PaginationDto pagination;
    }

    @Getter
    @Setter
    @Builder
    public static class PaginationDto {
        private int currentPage;
        private int totalPages;
        private long totalAnswers;
    }
}