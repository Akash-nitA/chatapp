package com.example.chatapp.services.implementations;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.chatapp.models.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	@Value("${jwt.secret}")
	private String secret;
	
	private static final long EXPIRATION=3600_000;
	
	public String generateToken(User user) {
		return Jwts.builder()
				.setSubject(user.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
}
