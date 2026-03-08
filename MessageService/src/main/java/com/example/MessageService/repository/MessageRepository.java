package com.example.MessageService.repository;

import com.example.MessageService.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByReceiverId(String receiverId);

    List<Message> findByGroupId(String groupId);
}
