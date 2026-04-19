package com.amazon_lite.enums;

public enum Role {
    USER,
    SUPERADMIN,
    ADMIN;

    public String getAuthority() {
        return this.name();
    }
}