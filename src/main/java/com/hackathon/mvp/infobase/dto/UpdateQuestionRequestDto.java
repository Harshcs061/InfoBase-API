package com.hackathon.mvp.infobase.dto;

import com.hackathon.mvp.infobase.enums.QuestionVisibility;
import com.hackathon.mvp.infobase.model.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class UpdateQuestionRequestDto {
    private Long id;
    private String title;
    private String description;
    private Set<Tag> tags;                // actual Tag entities
    private String visibility; // enum
}