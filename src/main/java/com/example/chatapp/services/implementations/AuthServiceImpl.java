package com.example.chatapp.services.implementations;

import java.time.LocalDateTime;

import com.example.chatapp.models.User;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.chatapp.dto.LoginRequest;
import com.example.chatapp.dto.LoginResponse;
import com.example.chatapp.dto.RegisterRequest;
import com.example.chatapp.dto.UserResponse;
import com.example.chatapp.repositories.UserRepository;
import com.example.chatapp.services.AuthService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
	@Override
	public UserResponse register(RegisterRequest request) {
		
		// TODO Auto-generated method stub
		User user=User.builder()
				.username(request.getUsername())
				.password(passwordEncoder.encode(request.getPassword()))
				.email(request.getEmail())
				.createdAt(LocalDateTime.now())
				.build();
		User saved=userRepository.save(user);
		return UserResponse.from(saved);
	}
	@Override
	public LoginResponse login(LoginRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
