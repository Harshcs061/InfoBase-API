package com.hackathon.mvp.infobase.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthorDto {
    private String id;
    private String name;
    private String avatar;
}
