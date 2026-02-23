package com.example.ChatService.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    private String senderId;
    private String receiverId;
    private String groupId;
    private String content;
    private Long timestamp;

}

