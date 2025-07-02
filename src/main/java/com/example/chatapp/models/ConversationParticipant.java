package com.example.chatapp.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.example.chatapp.models.ConversationParticipantId;
import com.example.chatapp.models.User;

/**
 * conversation_participants join table
 */
@Entity
@Table(name = "conversation_participants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConversationParticipant {

    @EmbeddedId
    private ConversationParticipantId id;

    @MapsId("conversationId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "joined_at", nullable = false)
    private LocalDateTime joinedAt;
}


