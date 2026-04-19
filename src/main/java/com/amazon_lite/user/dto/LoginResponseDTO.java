package com.amazon_lite.user.dto;

import com.amazon_lite.enums.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {

    private String message;
    private String token;
    private String email;
    private Role role;

}