package com.example.GroupService.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GroupResponse {
    private Long id;
    private String name;
    private String createdBy;
    private List<String> members;
}