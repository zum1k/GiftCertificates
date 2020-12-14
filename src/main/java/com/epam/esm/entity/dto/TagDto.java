package com.epam.esm.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class TagDto {
    private int id;
    @NotNull(message = "Need to enter a name")
    @Size(min = 2, max = 45, message = "Name size must over 2 and less than 45 symbols")
    private String name;
    private ZonedDateTime createDate;
    private ZonedDateTime lastUpdateDate;

    public TagDto(String name) {
        this.name = name;
    }

}
