package com.example.ChatService.kafka;

import com.example.ChatService.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatProducer {

    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;

    public void send(ChatMessage message) {
        kafkaTemplate.send("chat-topic", message);
    }
}