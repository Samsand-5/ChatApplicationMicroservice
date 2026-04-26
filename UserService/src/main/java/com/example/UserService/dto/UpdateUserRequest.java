package com.example.UserService.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String name;
    private String profilePic;
}