package com.example.chatapp.dto;

import java.time.LocalDateTime;

import com.example.chatapp.models.User;

import lombok.Data;

@Data
public class UserResponse {
	private Long id;
	private String username;
	private String email;
	private LocalDateTime createdAt;
	
	public static UserResponse from(User user) {
		UserResponse dto=new UserResponse();
		dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
		return dto;
	}
}
