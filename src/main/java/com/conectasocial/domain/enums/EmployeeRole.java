package com.conectasocial.domain.enums;

public enum EmployeeRole {
    ADMIN("ADMIN"),
    MANAGER("MANAGER"),
    VOLUNTEER("VOLUNTEER");

    private String value;

    EmployeeRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
