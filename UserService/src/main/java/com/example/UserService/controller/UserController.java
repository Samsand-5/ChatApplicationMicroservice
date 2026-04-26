package com.example.UserService.controller;

import com.example.UserService.dto.UpdateUserRequest;
import com.example.UserService.dto.UserResponse;
import com.example.UserService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable String userId,
            @RequestBody UpdateUserRequest request) {

        return ResponseEntity.ok(userService.updateUser(userId, request));
    }

    @PostMapping("/{userId}/fcm")
    public ResponseEntity<String> saveFcmToken(
            @PathVariable String userId,
            @RequestParam String token) {

        userService.saveFcmToken(userId, token);
        return ResponseEntity.ok("Token saved");
    }
}