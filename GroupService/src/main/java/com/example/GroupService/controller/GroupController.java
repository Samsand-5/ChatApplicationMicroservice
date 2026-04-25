package com.example.GroupService.controller;

import com.example.GroupService.dto.AddMemberRequest;
import com.example.GroupService.dto.CreateGroupRequest;
import com.example.GroupService.dto.GroupResponse;
import com.example.GroupService.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<GroupResponse> createGroup(
            @RequestBody CreateGroupRequest request) {

        return ResponseEntity.ok(groupService.createGroup(request));
    }

    @PostMapping("/{groupId}/members")
    public ResponseEntity<String> addMember(
            @PathVariable Long groupId,
            @RequestBody AddMemberRequest request) {

        groupService.addMember(groupId, request.getUserId());
        return ResponseEntity.ok("Member added");
    }

    @DeleteMapping("/{groupId}/members/{userId}")
    public ResponseEntity<String> removeMember(
            @PathVariable Long groupId,
            @PathVariable String userId) {

        groupService.removeMember(groupId, userId);
        return ResponseEntity.ok("Member removed");
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupResponse> getGroup(@PathVariable Long groupId) {
        return ResponseEntity.ok(groupService.getGroup(groupId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<GroupResponse>> getUserGroups(
            @PathVariable String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                groupService.getUserGroups(userId, page, size)
        );
    }

    @GetMapping
    public ResponseEntity<Page<GroupResponse>> getAllGroups(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(groupService.getAllGroups(page, size));
    }
}