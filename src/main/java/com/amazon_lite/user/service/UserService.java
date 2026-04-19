package com.amazon_lite.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amazon_lite.user.dto.UserRequestDTO;
import com.amazon_lite.user.dto.UserResponseDTO;
import com.amazon_lite.user.entity.User;
import com.amazon_lite.user.mapper.UserMapper;
import com.amazon_lite.user.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public UserResponseDTO createUser(UserRequestDTO request){
        User user = UserMapper.toEntity(request);
        repo.save(user);
        return UserMapper.toDTO(user);
    }

    public List<UserResponseDTO> getAllUsers(){
        List<User> users = repo.findAll();
        return users.stream().map(UserMapper::toDTO).toList();
    }

}
