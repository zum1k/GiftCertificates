package com.epam.esm.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.stream.Stream;

public enum DateSortType {
    ASC("ASC"), DESC("DESC");
    private final String value;

//<editor-fold defaultstate="collapsed" desc="delombok">
//</editor-fold>
    /**
     * Convert value from JSON to Enum.
     *
     * @param jsonValue -- from JSON object
     * @return enum value
     */
    @JsonCreator
    public static DateSortType fromJsonValue(String jsonValue) {
        return Stream.of(values()).filter(title -> title.getValue().equals(jsonValue)).findFirst().orElse(null);
    }

    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public String getValue() {
        return this.value;
    }

    @SuppressWarnings("all")
    private DateSortType(final String value) {
        this.value = value;
    }
    //</editor-fold>
}
