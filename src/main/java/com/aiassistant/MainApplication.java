package com.aiassistant;

import com.aiassistant.service.AssistantService;

public class MainApplication {

    public static void main(String[] args) {

        ChatWindow window = new ChatWindow();
        AssistantService assistantService = new AssistantService();

        // Voice input
        window.getSpeakButton().addActionListener(e -> assistantService.handleVoiceInput(window));

        // Text input with Send button
        window.getSendButton().addActionListener(e -> sendTypedMessage(window, assistantService));

        // Text input with Enter key
        window.getInputField().addActionListener(e -> sendTypedMessage(window, assistantService));
    }

    private static void sendTypedMessage(ChatWindow window, AssistantService assistantService) {
        String userText = window.getInputField().getText().trim();

        if (userText.isBlank()) {
            return;
        }

        window.getSendButton().setEnabled(false);
        window.getSpeakButton().setEnabled(false);
        window.getInputField().setText("");

        assistantService.processUserMessage(userText, window);
    }
}