package com.amazon_lite.user.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.amazon_lite.enums.Role;
import com.amazon_lite.security.JwtUtil;
import com.amazon_lite.user.dto.LoginRequestDTO;
import com.amazon_lite.user.dto.LoginResponseDTO;
import com.amazon_lite.user.dto.UserRequestDTO;
import com.amazon_lite.user.dto.UserResponseDTO;
import com.amazon_lite.user.entity.User;
import com.amazon_lite.user.mapper.UserMapper;
import com.amazon_lite.user.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository repo, BCryptPasswordEncoder encoder, JwtUtil jwtUtil) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    public UserResponseDTO createUser(UserRequestDTO request) {
        User user = UserMapper.toEntity(request);

        if (repo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(encoder.encode(request.getPassword()));

        repo.save(user);
        return UserMapper.toDTO(user);
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        User user = repo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String role = user.getRole() != null ? user.getRole().toString() : Role.USER.toString();
        String token = jwtUtil.generateToken(user.getEmail(), role);
        return LoginResponseDTO.builder()
                .message("Login successful")
                .token(token)
                .email(user.getEmail())
                .build();
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = repo.findAll();
        return users.stream().map(UserMapper::toDTO).toList();
    }

}
