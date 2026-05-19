package com.aiassistant.repository;

import com.aiassistant.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

    // 🆕 گرفتن پیام‌های یک conversation خاص (به ترتیب)
    List<ChatMessage> findByConversationIdOrderByIdAsc(Long conversationId);
}