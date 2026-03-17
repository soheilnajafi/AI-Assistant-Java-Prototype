package com.aiassistant.service;

import com.aiassistant.OpenAIClient;
import com.aiassistant.WhisperClient;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AssistantService {

    private List<String> conversationHistory = new ArrayList<>();

    public String processAudio(File audioFile) throws Exception {

        System.out.println("🧠 Processing speech...");

        String speechText = WhisperClient.transcribe(audioFile);

        System.out.println("User said: " + speechText);

        return speechText;
    }

    public String getAIResponse(String userMessage) throws Exception {

        conversationHistory.add("User: " + userMessage);

        StringBuilder prompt = new StringBuilder();

        for (String message : conversationHistory) {
            prompt.append(message).append("\n");
        }

        String response = OpenAIClient.askAI(prompt.toString());

        conversationHistory.add("AI: " + response);

        return response;
    }
}