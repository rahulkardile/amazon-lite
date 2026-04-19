package com.amazon_lite.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon_lite.user.dto.LoginRequestDTO;
import com.amazon_lite.user.dto.LoginResponseDTO;
import com.amazon_lite.user.dto.UserRequestDTO;
import com.amazon_lite.user.dto.UserResponseDTO;
import com.amazon_lite.user.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO request){
        return userService.createUser(request);
    }

    @PostMapping("/login")
    public LoginResponseDTO userLogin(@RequestBody LoginRequestDTO request){
        return userService.login(request);
    }

    @GetMapping("/all")
    public List<UserResponseDTO> getAllUsers(){
        return userService.getAllUsers();
    }

}
