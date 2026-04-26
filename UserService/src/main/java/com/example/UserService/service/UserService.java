package com.example.UserService.service;

import com.example.UserService.dto.UpdateUserRequest;
import com.example.UserService.dto.UserResponse;
import com.example.UserService.entity.User;
import com.example.UserService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;

    public UserResponse getUser(String userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return map(user);
    }

    public UserResponse updateUser(String userId, UpdateUserRequest request) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(request.getName());
        user.setProfilePic(request.getProfilePic());

        return map(userRepo.save(user));
    }

    public void saveFcmToken(String userId, String token) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFcmToken(token);
        userRepo.save(user);
    }

    private UserResponse map(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .profilePic(user.getProfilePic())
                .build();
    }
}