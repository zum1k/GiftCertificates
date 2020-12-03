package com.epam.esm.entity.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TagDto {
    private int id;
    @NotNull(message = "Need to enter a name")
    @Size(min = 2, max = 45, message = "Name size must over 2 and less than 45 symbols")
    private String name;

    public TagDto(String name) {
        this.name = name;
    }

    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public int getId() {
        return this.id;
    }

    @SuppressWarnings("all")
    public String getName() {
        return this.name;
    }

    @SuppressWarnings("all")
    public void setId(final int id) {
        this.id = id;
    }

    @SuppressWarnings("all")
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    @SuppressWarnings("all")
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TagDto)) return false;
        final TagDto other = (TagDto) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        return true;
    }

    @SuppressWarnings("all")
    protected boolean canEqual(final Object other) {
        return other instanceof TagDto;
    }

    @Override
    @SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings("all")
    public String toString() {
        return "TagDto(id=" + this.getId() + ", name=" + this.getName() + ")";
    }

    @SuppressWarnings("all")
    public TagDto() {
    }
    //</editor-fold>
}
