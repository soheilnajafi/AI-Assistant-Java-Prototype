package com.aiassistant;

import audio.MicrophoneRecorder;
import com.aiassistant.service.AssistantService;

import java.io.File;

public class MainApplication {

    public static void main(String[] args) {

        // Create chat window
        ChatWindow window = new ChatWindow();

        // Create assistant service
        AssistantService assistantService = new AssistantService();

        // When Speak button is pressed
        window.getSpeakButton().addActionListener(e -> {

            // Run in background thread so UI does not freeze
            new Thread(() -> {

                try {

                    System.out.println("🎤 Please speak...");

                    // Record audio for 7 seconds
                    MicrophoneRecorder.recordAudio(7);

                    File audioFile = new File("recorded_audio.wav");

                    // Send audio to AssistantService
                    String speechText = assistantService.processAudio(audioFile);

                    // Show user message
                    window.addUserMessage(speechText);

                    // Get AI response
                    String response = assistantService.getAIResponse(speechText);

                    // Show AI response
                    window.addAIMessage(response);

                } catch (Exception ex) {

                    ex.printStackTrace();

                }

            }).start();

        });

    }
}