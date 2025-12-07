package com.hackathon.mvp.infobase.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAnswerRequestDto {
    @NotBlank(message = "Answer body is required")
    private String body;
}