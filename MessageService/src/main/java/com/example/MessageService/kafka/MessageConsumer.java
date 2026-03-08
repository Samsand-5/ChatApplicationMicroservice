package com.example.MessageService.kafka;

import com.example.MessageService.dto.ChatMessageDTO;
import com.example.MessageService.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageConsumer {

    private final MessageService messageService;

    @KafkaListener(topics = "chat-topic", groupId = "message-group")
    public void consume(ChatMessageDTO message) {

        messageService.saveMessage(message);
    }
}
