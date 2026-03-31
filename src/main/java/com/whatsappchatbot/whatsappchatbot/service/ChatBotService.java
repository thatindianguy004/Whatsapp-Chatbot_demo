package com.whatsappchatbot.whatsappchatbot.service;

import org.springframework.stereotype.Service;

@Service
public class ChatBotService {
    public String getReply(String message) {
        if (message == null || message.isBlank()) {
            return "Please send a valid message.";
        }

        String cleaned = message.trim().toLowerCase();

        return switch (cleaned) {
            case "hi", "hello", "hey"   -> "Hello! How can I help you?";
            case "bye", "goodbye"       -> "Goodbye! Have a great day!";
            case "help"                 -> "Available commands: Hi, Bye, Help";
            default -> "Sorry, I didn't understand that. Type 'Help' to see commands.";
        };
    }
}
