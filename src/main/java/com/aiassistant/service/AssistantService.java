package com.aiassistant.service;

import com.aiassistant.ChatWindow;
import com.aiassistant.OpenAIClient;
import com.aiassistant.WhisperClient;

import javax.swing.SwingUtilities;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AssistantService {

    private final List<String> conversationHistory = new ArrayList<>();

    public void processAudio(File audioFile, ChatWindow window) {
        new Thread(() -> {
            try {
                String speechText = WhisperClient.transcribe(audioFile);
                speechText = cleanSpeech(speechText);

                if (speechText.isBlank()) {
                    SwingUtilities.invokeLater(() -> {
                        window.setStatus("Ready");
                        window.getSpeakButton().setEnabled(true);
                        window.getSendButton().setEnabled(true);
                    });
                    return;
                }

                String finalSpeechText = speechText;

                SwingUtilities.invokeLater(() -> {
                    window.addUserMessage(finalSpeechText);
                    window.setStatus("Thinking...");
                });

                String response = getAIResponse(finalSpeechText);

                if (response == null || response.isBlank()) {
                    SwingUtilities.invokeLater(() -> {
                        window.setStatus("Ready");
                        window.getSpeakButton().setEnabled(true);
                        window.getSendButton().setEnabled(true);
                    });
                    return;
                }

                SwingUtilities.invokeLater(() -> {
                    window.addAIMessage(response);
                    window.setStatus("Ready");
                    window.getSpeakButton().setEnabled(true);
                    window.getSendButton().setEnabled(true);
                });

            } catch (Exception e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() -> {
                    window.setStatus("Error");
                    window.getSpeakButton().setEnabled(true);
                    window.getSendButton().setEnabled(true);
                });
            }
        }).start();
    }

    public void processUserMessage(String userText, ChatWindow window) {
        if (userText == null || userText.isBlank()) {
            SwingUtilities.invokeLater(() -> {
                window.getSpeakButton().setEnabled(true);
                window.getSendButton().setEnabled(true);
            });
            return;
        }

        window.addUserMessage(userText);
        window.setStatus("Thinking...");

        new Thread(() -> {
            try {
                String response = getAIResponse(userText);

                if (response == null || response.isBlank()) {
                    SwingUtilities.invokeLater(() -> {
                        window.setStatus("Ready");
                        window.getSpeakButton().setEnabled(true);
                        window.getSendButton().setEnabled(true);
                    });
                    return;
                }

                SwingUtilities.invokeLater(() -> {
                    window.addAIMessage(response);
                    window.setStatus("Ready");
                    window.getSpeakButton().setEnabled(true);
                    window.getSendButton().setEnabled(true);
                });

            } catch (Exception e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() -> {
                    window.setStatus("Error");
                    window.getSpeakButton().setEnabled(true);
                    window.getSendButton().setEnabled(true);
                });
            }
        }).start();
    }

    private String getAIResponse(String userMessage) throws Exception {
        if (userMessage == null || userMessage.isBlank()) {
            return "";
        }

        conversationHistory.add("User: " + userMessage);

        StringBuilder prompt = new StringBuilder();
        for (String msg : conversationHistory) {
            prompt.append(msg).append("\n");
        }

        String response = OpenAIClient.askAI(prompt.toString()).trim();
        conversationHistory.add("AI: " + response);

        return response;
    }

    private String cleanSpeech(String speechText) {
        if (speechText == null) {
            return "";
        }

        speechText = speechText.replaceAll("(?i)subs by.*", "").trim();
        speechText = speechText.replaceAll("\\s+", " ").trim();

        if (speechText.matches("^[\\p{Punct}\\s]+$")) {
            return "";
        }

        if (speechText.length() < 2) {
            return "";
        }

        return speechText;
    }
}