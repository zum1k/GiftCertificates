package com.epam.esm.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TagDto {
    private int id;
    //TODO NotNull @Annotation
    private String name;

    public TagDto(String name) {
        this.name = name;
    }
}
