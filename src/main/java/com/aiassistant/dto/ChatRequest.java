package com.aiassistant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ChatRequest {

    // 🆕 Conversation ID
    private Long conversationId;

    @NotBlank(message = "Message cannot be empty")
    @Size(min = 2, max = 500, message = "Message must be between 2 and 500 characters")
    private String message;

    public ChatRequest() {
    }

    // 🆕 Getter/Setter برای conversationId
    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}