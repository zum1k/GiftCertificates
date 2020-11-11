package com.epam.esm.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum DateSortType {
    ASC("ASC"),
    DESC("DESC");
    private final String value;
    /**
     * Convert value from JSON to Enum.
     *
     * @param jsonValue -- from JSON object
     * @return enum value
     */
    @JsonCreator
    public static DateSortType fromJsonValue(String jsonValue) {
        return Stream.of(values())
                .filter(title -> title.getValue().equals(jsonValue))
                .findFirst().orElse(null);
    }
}
