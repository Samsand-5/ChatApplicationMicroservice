package com.example.ChatService.controller;


import com.example.ChatService.dto.ChatMessage;
import com.example.ChatService.kafka.ChatProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatProducer producer;

    @MessageMapping("/chat.send")
    public void send(ChatMessage message) {
        message.setTimestamp(System.currentTimeMillis());
        producer.send(message);
    }
}
