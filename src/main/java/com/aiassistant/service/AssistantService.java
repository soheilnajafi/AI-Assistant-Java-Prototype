package com.aiassistant.service;

import com.aiassistant.client.OpenAIClientService;
import com.aiassistant.entity.ChatMessage;
import com.aiassistant.repository.ChatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistantService {

    private static final Logger logger = LoggerFactory.getLogger(AssistantService.class);

    private final OpenAIClientService openAIClient;
    private final ChatRepository chatRepository;

    public AssistantService(OpenAIClientService openAIClient, ChatRepository chatRepository) {
        this.openAIClient = openAIClient;
        this.chatRepository = chatRepository;
    }

    public String getResponse(Long conversationId, String message) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be empty.");
        }

        try {
            logger.info("Fetching conversation history");

            List<ChatMessage> history =
                    chatRepository.findByConversationIdOrderByIdAsc(conversationId);

            logger.info("Building conversation context");

            StringBuilder context = new StringBuilder();

            context.append("You are an AI assistant continuing an existing conversation. ")
                    .append("Use the previous messages below as context when answering the latest user message.\n\n");

            for (ChatMessage msg : history) {
                context.append("Previous user message: ")
                        .append(msg.getUserMessage())
                        .append("\n");

                context.append("Previous AI response: ")
                        .append(msg.getAiResponse())
                        .append("\n");
            }

            context.append("\nCurrent user message: ")
                    .append(message);

            logger.info("Built context: {}", context);

            logger.info("Calling OpenAI service");

            String response = openAIClient.getResponse(context.toString());

            ChatMessage chatMessage =
                    new ChatMessage(conversationId, message, response);

            chatRepository.save(chatMessage);

            logger.info("Response received successfully and saved to database");

            return response;

        } catch (Exception e) {
            logger.error("Error calling OpenAI service", e);
            throw new RuntimeException("Failed to get AI response.");
        }
    }

    public Page<ChatMessage> getChatHistory(int page, int size) {
        logger.info("Fetching paginated chat history from database");

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        return chatRepository.findAll(pageable);
    }
}