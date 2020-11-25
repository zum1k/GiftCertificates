package com.epam.esm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Tag {
    private int tagId;
    private String name;

    public Tag(String name) {
        this.name = name;
    }
}
