package com.example.chatapp.models;

import java.time.LocalDateTime;

import java.util.List;

import com.example.chatapp.models.ConversationParticipant;
import com.example.chatapp.models.Message;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Table(name="conversations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conversation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	
	@Column(name="is_group",nullable=false)
	private Boolean isGroup;
	
	private String name;
	
	@Column(name="created_at",nullable=false)
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private List<Message> messages;
	
	@OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private List<ConversationParticipant> participants;
	
}
