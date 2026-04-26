package com.example.UserService.service;


import com.example.UserService.dto.RegisterRequest;
import com.example.UserService.dto.UserCreatedEvent;
import com.example.UserService.entity.User;
import com.example.UserService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final UserEventProducer producer;

    public void register(RegisterRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role("ROLE_USER")
                .build();

        repository.save(user);

        // 🔥 SEND EVENT (ASYNC)
        producer.send(
                new UserCreatedEvent(
                        request.getUsername(),
                        request.getEmail()
                )
        );
    }
}
