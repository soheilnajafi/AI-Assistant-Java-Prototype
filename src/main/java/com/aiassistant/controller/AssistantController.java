package com.aiassistant.controller;

import com.aiassistant.dto.ChatRequest;
import com.aiassistant.dto.ChatResponse;
import com.aiassistant.entity.ChatMessage;
import com.aiassistant.service.AssistantService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assistant")
@CrossOrigin(origins = "*")
public class AssistantController {

    private static final Logger logger = LoggerFactory.getLogger(AssistantController.class);

    private final AssistantService assistantService;

    public AssistantController(AssistantService assistantService) {
        this.assistantService = assistantService;
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@Valid @RequestBody ChatRequest request) {
        logger.info("Incoming chat request received");

        String aiResponse = assistantService.getResponse(
                request.getConversationId(),
                request.getMessage()
        );

        logger.info("Returning successful chat response");
        return ResponseEntity.ok(ChatResponse.success(aiResponse));
    }

    @GetMapping("/history")
    public ResponseEntity<Page<ChatMessage>> getHistory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        logger.info("Fetching paginated chat history");

        return ResponseEntity.ok(assistantService.getChatHistory(page, size));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Assistant API is running");
    }
}