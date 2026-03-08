package com.example.MessageService.service;

import com.example.MessageService.dto.ChatMessageDTO;
import com.example.MessageService.entity.Message;
import com.example.MessageService.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository repository;

    public void saveMessage(ChatMessageDTO dto) {

        Message message = Message.builder()
                .senderId(dto.getSenderId())
                .receiverId(dto.getReceiverId())
                .groupId(dto.getGroupId())
                .content(dto.getContent())
                .timestamp(dto.getTimestamp())
                .build();

        repository.save(message);
    }
}