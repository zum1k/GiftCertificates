package com.epam.esm.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Tag {
    private int tagId;
    private String name;

    public Tag(String name){
        this.name = name;
    }
}
