package com.hackathon.mvp.infobase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequest {
    private String title;
    private String body;
    private String authorEmail; // assume simple identification by email for now
}