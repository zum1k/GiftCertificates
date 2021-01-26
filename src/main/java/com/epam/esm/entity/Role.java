package com.epam.esm.entity;

public enum Role {
  ADMIN("admin"),
  USER("user");
  private final String value;

  Role(String value) {
    this.value = value;
  }

  String getValue() {
    return value;
  }
}
