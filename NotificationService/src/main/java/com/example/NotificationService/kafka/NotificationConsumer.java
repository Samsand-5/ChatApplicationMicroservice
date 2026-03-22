package com.example.NotificationService.kafka;

import com.example.NotificationService.dto.ChatMessageDTO;
import com.example.NotificationService.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "chat-topic", groupId = "notification-group")
    public void consume(ChatMessageDTO message) {

        // ⚠️ Replace with DB or Redis lookup
        String userFcmToken = getUserToken(message.getReceiverId());

        if (userFcmToken != null) {
            notificationService.sendNotification(userFcmToken, message);
        }
    }

    private String getUserToken(String userId) {
        // TODO: fetch from DB / Redis
        /*
        Don’t send notification if user is ONLINE
        Use Redis:
        online:userId → true
        Then:
        if (!isUserOnline(receiverId)) {
        sendNotification();
        }
         */
        return "USER_FCM_TOKEN";
    }
}