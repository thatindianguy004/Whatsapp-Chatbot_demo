package com.whatsappchatbot.whatsappchatbot.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WhatsappMessage {
    private String from;    // sender's phone number
    private String to;      // recipient's phone number
    private String message;

}
