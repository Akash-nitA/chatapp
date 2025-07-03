package com.example.chatapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.chatapp.models.User;

import jakarta.validation.constraints.NotBlank;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	Optional<User> findByUsername(@NotBlank String username);
	

}
