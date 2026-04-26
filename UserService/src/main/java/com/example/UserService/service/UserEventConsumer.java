package com.example.UserService.service;

import com.example.UserService.dto.UserCreatedEvent;
import com.example.UserService.entity.User;
import com.example.UserService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventConsumer {

    private final UserRepository userRepository;

    @KafkaListener(
            topics = "user-created-topic",
            groupId = "user-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(UserCreatedEvent event) {

        // Avoid duplicate
        if (userRepository.existsById(event.getUserId())) {
            return;
        }

        User user = User.builder()
                .id(event.getUserId())
                .email(event.getEmail())
                .build();

        userRepository.save(user);
    }
}