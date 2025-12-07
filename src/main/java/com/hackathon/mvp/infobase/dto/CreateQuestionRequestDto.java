package com.hackathon.mvp.infobase.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateQuestionRequestDto {
    private String title;
    private String description;
    private List<Long> tags;
    private String visibility;
    private Long askedBy;
    private Long related_project;
}
