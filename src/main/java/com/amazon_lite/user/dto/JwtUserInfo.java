package com.amazon_lite.user.dto;

public class JwtUserInfo {
    private String email;
    private String role;

    public JwtUserInfo(String email, String role) {
        this.email = email;
        this.role = role;
    }

    public String getEmail() { return email; }
    public String getRole() { return role; }
}