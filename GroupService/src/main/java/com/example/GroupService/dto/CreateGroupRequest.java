package com.example.GroupService.dto;

import lombok.Data;

@Data
public class CreateGroupRequest {
    private String name;
    private String createdBy;
}