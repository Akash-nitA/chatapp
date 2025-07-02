package com.example.chatapp.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

@Data
public class RegisterRequest {
	
	@NotBlank(message="username is required")
	@Size(min=3,max=50)
	private String username;
	
	@NotBlank(message="Password is required")
	@Size(min=6,message="Password should be atleast 6 characters long")
	private String password;
	
	@Email(message = "Provide a valid email")
	private String email;

}
