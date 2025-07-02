package com.example.chatapp.models;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(nullable=false,unique=true)
	private String username;
	
	@Column(name="password",nullable=false)
	private String password;
	
	@Column(unique=true)
	private String email;
	
	@Column(name="created_at", nullable=false)
	private LocalDateTime createdAt;
	
	@Column(name = "last_login")
    private LocalDateTime lastLogin;

    private String status;
    
    @OneToMany(mappedBy="sender",cascade=CascadeType.ALL,orphanRemoval=true)
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private List<Message> messages;
    
    @OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval=true)
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private List<ConversationParticipant> conversationParticipants;
}

