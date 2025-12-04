package com.hackathon.mvp.infobase.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AskedByDto {
    private Long id;
    private String name;
    private String avatar;
}
