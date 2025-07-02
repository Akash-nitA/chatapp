package com.example.chatapp.services;

import com.example.chatapp.dto.LoginRequest;
import com.example.chatapp.dto.LoginResponse;
import com.example.chatapp.dto.RegisterRequest;
import com.example.chatapp.dto.UserResponse;

public interface AuthService {
	UserResponse register(RegisterRequest request);
	LoginResponse login(LoginRequest request);
}
