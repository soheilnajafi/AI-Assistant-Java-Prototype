package com.aiassistant;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChatWindow extends JFrame {

    private final JTextArea chatArea;
    private final JButton speakButton;
    private final JTextField inputField;
    private final JButton sendButton;
    private final JLabel statusLabel;

    public ChatWindow() {

        setTitle("AI Assistant");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Color backgroundColor = new Color(30, 30, 30);
        Color panelColor = new Color(40, 40, 40);
        Color textColor = new Color(230, 230, 230);
        Color inputColor = new Color(55, 55, 55);
        Color buttonColor = new Color(70, 70, 70);
        Color borderColor = new Color(90, 90, 90);

        getContentPane().setBackground(backgroundColor);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setBackground(panelColor);
        chatArea.setForeground(textColor);
        chatArea.setCaretColor(textColor);
        chatArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        chatArea.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(borderColor));
        scrollPane.getViewport().setBackground(panelColor);

        statusLabel = new JLabel("Ready");
        statusLabel.setForeground(textColor);
        statusLabel.setBackground(backgroundColor);
        statusLabel.setOpaque(true);
        statusLabel.setBorder(new EmptyBorder(6, 10, 6, 10));

        speakButton = new JButton("Speak");
        speakButton.setBackground(buttonColor);
        speakButton.setForeground(textColor);
        speakButton.setFocusPainted(false);

        inputField = new JTextField();
        inputField.setBackground(inputColor);
        inputField.setForeground(textColor);
        inputField.setCaretColor(textColor);
        inputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor),
                new EmptyBorder(6, 8, 6, 8)
        ));

        sendButton = new JButton("Send");
        sendButton.setBackground(buttonColor);
        sendButton.setForeground(textColor);
        sendButton.setFocusPainted(false);

        JPanel bottomPanel = new JPanel(new BorderLayout(8, 0));
        bottomPanel.setBackground(backgroundColor);
        bottomPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
        bottomPanel.add(speakButton, BorderLayout.WEST);
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(statusLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    public void addUserMessage(String message) {
        chatArea.append("You: " + message + "\n\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    public void addAIMessage(String message) {
        chatArea.append("AI: " + message + "\n\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    public void setStatus(String status) {
        statusLabel.setText(status);
    }

    public JButton getSpeakButton() {
        return speakButton;
    }

    public JTextField getInputField() {
        return inputField;
    }

    public JButton getSendButton() {
        return sendButton;
    }
}