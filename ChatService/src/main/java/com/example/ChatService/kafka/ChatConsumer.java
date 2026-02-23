package com.example.ChatService.kafka;

import com.example.ChatService.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(
            topics = "chat-topic",
            groupId = "chat-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(ChatMessage message) {

        if (message.getGroupId() != null) {
            messagingTemplate.convertAndSend(
                    "/topic/group/" + message.getGroupId(),
                    message
            );
        } else {
            messagingTemplate.convertAndSend(
                    "/queue/user/" + message.getReceiverId(),
                    message
            );
        }
    }
}