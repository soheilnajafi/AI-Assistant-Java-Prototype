package com.aiassistant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import audio.MicrophoneRecorder;
import com.aiassistant.ChatWindow;
import com.aiassistant.OpenAIClient;
import com.aiassistant.WhisperClient;

import javax.swing.SwingUtilities;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AssistantService {

    private static final Logger logger = LoggerFactory.getLogger(AssistantService.class);

    private final List<String> conversationHistory = new ArrayList<>();

    public void handleVoiceInput(ChatWindow window) {
        window.getSpeakButton().setEnabled(false);
        window.getSendButton().setEnabled(false);

        new Thread(() -> {
            try {
                SwingUtilities.invokeLater(() -> window.setStatus("🎤 Listening... speak now"));

                MicrophoneRecorder.recordAudio(6);

                File audioFile = new File("recorded_audio.wav");

                SwingUtilities.invokeLater(() -> window.setStatus("Processing speech..."));

                processAudio(audioFile, window);

            } catch (Exception e) {
                logger.error("Error during voice input handling", e);
                showError(window);
            }
        }).start();
    }

    public void processAudio(File audioFile, ChatWindow window) {
        new Thread(() -> {
            try {
                String speechText = WhisperClient.transcribe(audioFile);
                speechText = cleanSpeech(speechText);

                logger.info("Transcribed speech: {}", speechText);

                if (speechText.isBlank()) {
                    resetWindow(window);
                    return;
                }

                final String finalSpeechText = speechText;

                SwingUtilities.invokeLater(() -> {
                    window.addUserMessage(finalSpeechText);
                    window.setStatus("Thinking...");
                });

                handleAIResponse(finalSpeechText, window);

            } catch (Exception e) {
                logger.error("Error processing audio input", e);
                showError(window);
            }
        }).start();
    }

    public void processUserMessage(String userText, ChatWindow window) {
        if (userText == null || userText.isBlank()) {
            resetButtonsOnly(window);
            return;
        }

        SwingUtilities.invokeLater(() -> {
            window.addUserMessage(userText);
            window.setStatus("Thinking...");
        });

        new Thread(() -> {
            try {
                handleAIResponse(userText, window);
            } catch (Exception e) {
                logger.error("Error processing user message", e);
                showError(window);
            }
        }).start();
    }

    public String processText(String input) {
        if (input == null || input.isBlank()) {
            return "Message cannot be empty.";
        }

        try {
            return getAIResponse(input);
        } catch (IllegalStateException e) {
            logger.error("Configuration error in processText", e);
            return "Configuration error: API key is missing.";
        } catch (Exception e) {
            logger.error("Error processing API text request", e);
            return "Error processing request.";
        }
    }

    private void handleAIResponse(String userText, ChatWindow window) throws Exception {
        String response = getAIResponse(userText);

        if (response == null || response.isBlank()) {
            resetWindow(window);
            return;
        }

        SwingUtilities.invokeLater(() -> {
            window.addAIMessage(response);
            window.setStatus("Ready");
            enableButtons(window);
        });
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

    private void resetWindow(ChatWindow window) {
        SwingUtilities.invokeLater(() -> {
            window.setStatus("Ready");
            enableButtons(window);
        });
    }

    private void resetButtonsOnly(ChatWindow window) {
        SwingUtilities.invokeLater(() -> enableButtons(window));
    }

    private void showError(ChatWindow window) {
        SwingUtilities.invokeLater(() -> {
            window.setStatus("Error");
            enableButtons(window);
        });
    }

    private void enableButtons(ChatWindow window) {
        window.getSpeakButton().setEnabled(true);
        window.getSendButton().setEnabled(true);
    }
}