package com.influa.influa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.influa.influa.dtos.user.LoginResponse;
import com.influa.influa.dtos.user.UserLoginDTO;
import com.influa.influa.services.user.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid UserLoginDTO dto) {
        LoginResponse response = userService.authenticate(dto);
        
        return ResponseEntity.ok(response);
    }

}
