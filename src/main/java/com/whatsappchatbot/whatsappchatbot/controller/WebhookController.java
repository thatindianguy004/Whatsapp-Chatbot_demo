package com.whatsappchatbot.whatsappchatbot.controller;

import com.whatsappchatbot.whatsappchatbot.model.WhatsappMessage;
import com.whatsappchatbot.whatsappchatbot.service.ChatBotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/webhook")
public class WebhookController {
    private static final Logger log = LoggerFactory.getLogger(WebhookController.class);
    private final ChatBotService chatbotService;

    public WebhookController(ChatBotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    // GET /webhook — for verification (useful if integrating with real WhatsApp API later)
    @GetMapping
    public ResponseEntity<String> verify(@RequestParam(value = "hub.challenge", required = false) String challenge) {
        log.info("Webhook verified");
        return ResponseEntity.ok(challenge != null ? challenge : "Webhook is active");
    }

    // POST /webhook — main chatbot endpoint
    @PostMapping
    public ResponseEntity<Map<String, String>> receiveMessage(@RequestBody WhatsappMessage incoming) {
        log.info("Incoming message from [{}]: {}", incoming.getFrom(), incoming.getMessage());

        String reply = chatbotService.getReply(incoming.getMessage());
        log.info("Reply to [{}]: {}", incoming.getFrom(), reply);

        Map<String, String> response = Map.of(
                "to",      incoming.getFrom(),
                "from",    incoming.getTo(),
                "message", reply,
                "status",  "sent"
        );

        return ResponseEntity.ok(response);
    }
}
