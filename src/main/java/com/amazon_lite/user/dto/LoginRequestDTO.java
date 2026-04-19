package com.amazon_lite.user.dto;

import com.amazon_lite.enums.Role;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
    private Role role;
}
