package com.aiassistant.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🆕 Conversation ID (مهم‌ترین تغییر)
    private Long conversationId;

    private String userMessage;

    @Column(columnDefinition = "TEXT")
    private String aiResponse;

    private LocalDateTime createdAt;

    public ChatMessage() {
    }

    // 🟢 Constructor جدید با conversationId
    public ChatMessage(Long conversationId, String userMessage, String aiResponse) {
        this.conversationId = conversationId;
        this.userMessage = userMessage;
        this.aiResponse = aiResponse;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    // 🆕 Getter/Setter برای conversationId
    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getAiResponse() {
        return aiResponse;
    }

    public void setAiResponse(String aiResponse) {
        this.aiResponse = aiResponse;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}