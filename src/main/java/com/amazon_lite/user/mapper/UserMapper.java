package com.amazon_lite.user.mapper;

import com.amazon_lite.enums.Role;
import com.amazon_lite.user.dto.UserRequestDTO;
import com.amazon_lite.user.dto.UserResponseDTO;
import com.amazon_lite.user.entity.User;

public class UserMapper {

    public static User toEntity(UserRequestDTO dto) {
        return new User(
                null,
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                null
        );
    }

    public static UserResponseDTO toDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole() != null ? user.getRole().name() : Role.USER.name());
        return dto;
    }
}