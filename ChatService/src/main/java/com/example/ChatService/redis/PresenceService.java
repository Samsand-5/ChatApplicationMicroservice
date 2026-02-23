package com.example.ChatService.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PresenceService {

    private final RedisTemplate<String, String> redisTemplate;

    public void setOnline(String userId) {
        redisTemplate.opsForValue().set("online:" + userId, "1");
    }

    public void setOffline(String userId) {
        redisTemplate.delete("online:" + userId);
    }

    public boolean isOnline(String userId) {
        return redisTemplate.hasKey("online:" + userId);
    }
}