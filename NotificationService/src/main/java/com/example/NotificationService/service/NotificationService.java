package com.example.NotificationService.service;

import com.example.NotificationService.dto.ChatMessageDTO;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendNotification(String token, ChatMessageDTO chatMessage) {

        try {
            Notification notification = Notification.builder()
                    .setTitle("New Message from " + chatMessage.getSenderId())
                    .setBody(chatMessage.getContent())
                    .build();

            Message message = Message.builder()
                    .setToken(token)
                    .setNotification(notification)
                    .build();

            FirebaseMessaging.getInstance().send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}