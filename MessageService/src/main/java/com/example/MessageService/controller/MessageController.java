package com.example.MessageService.controller;

import com.example.MessageService.entity.Message;
import com.example.MessageService.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageRepository repository;

    @GetMapping("/private/{receiverId}")
    public List<Message> getPrivateMessages(
            @PathVariable String receiverId) {

        return repository.findByReceiverId(receiverId);
    }

    @GetMapping("/group/{groupId}")
    public List<Message> getGroupMessages(
            @PathVariable String groupId) {

        return repository.findByGroupId(groupId);
    }
}