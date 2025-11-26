package com.hackathon.mvp.infobase.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateQuestionRequestDto {

    private String title;          // required
    private String description;    // required
    private List<String> tags;     // optional, max 5
    private String visibility;     // optional: "public" or "organization"
    private UUID askedBy;
}
