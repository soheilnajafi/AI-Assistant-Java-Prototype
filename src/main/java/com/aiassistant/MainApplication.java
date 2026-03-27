package com.aiassistant;

import audio.MicrophoneRecorder;
import com.aiassistant.service.AssistantService;

import javax.swing.*;
import java.io.File;

public class MainApplication {

    public static void main(String[] args) {

        ChatWindow window = new ChatWindow();
        AssistantService assistantService = new AssistantService();

        // Voice input
        window.getSpeakButton().addActionListener(e -> {
            window.getSpeakButton().setEnabled(false);
            window.getSendButton().setEnabled(false);

            new Thread(() -> {
                try {
                    System.out.println("🎤 Please speak...");
                    MicrophoneRecorder.recordAudio(4);

                    File audioFile = new File("recorded_audio.wav");

                    SwingUtilities.invokeLater(() -> window.setStatus("Processing speech..."));

                    assistantService.processAudio(audioFile, window);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    SwingUtilities.invokeLater(() -> {
                        window.setStatus("Error");
                        window.getSpeakButton().setEnabled(true);
                        window.getSendButton().setEnabled(true);
                    });
                }
            }).start();
        });

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