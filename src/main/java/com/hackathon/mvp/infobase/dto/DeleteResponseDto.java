package com.hackathon.mvp.infobase.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DeleteResponseDto {
    private boolean success;
    private String message;
}