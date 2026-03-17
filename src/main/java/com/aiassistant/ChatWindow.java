package com.aiassistant;

import javax.swing.*;
import java.awt.*;

public class ChatWindow extends JFrame {

    private JTextArea chatArea;
    private JButton speakButton;

    public ChatWindow() {

        setTitle("AI Assistant");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(chatArea);

        speakButton = new JButton("Speak");

        add(scrollPane, BorderLayout.CENTER);
        add(speakButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void addUserMessage(String message) {
        chatArea.append("You: " + message + "\n\n");
    }

    public void addAIMessage(String message) {
        chatArea.append("AI: " + message + "\n\n");
    }

    public JButton getSpeakButton() {
        return speakButton;
    }
}