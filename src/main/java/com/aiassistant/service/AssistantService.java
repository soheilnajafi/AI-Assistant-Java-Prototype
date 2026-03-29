package com.aiassistant.service;

import org.springframework.stereotype.Service;

import com.aiassistant.OpenAIClientService;

@Service
public class AssistantService {

    private final OpenAIClientService openAIClient;

    public AssistantService() {
        this.openAIClient = new OpenAIClientService();
    }

    public String getResponse(String message) {
        try {
            return openAIClient.getResponse(message);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error getting AI response.";
        }
    }
}