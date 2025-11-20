package com.hackathon.mvp.infobase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {
    private String query;
    private List<AnswerResult> answers;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnswerResult {
        private String answerId;
        private String questionId;
        private String questionTitle;
        private String snippet;
        private String authorEmail;
    }
}