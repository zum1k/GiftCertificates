package com.epam.esm.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class TagDto {
    private int id;
    @NotNull
    @Size(min = 2, max = 45)
    private String name;

    public TagDto(String name) {
        this.name = name;
    }
}
