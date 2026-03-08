package com.example.MessageService.dto;

import lombok.Data;

@Data
public class ChatMessageDTO {

    private String senderId;
    private String receiverId;
    private String groupId;
    private String content;
    private Long timestamp;
}