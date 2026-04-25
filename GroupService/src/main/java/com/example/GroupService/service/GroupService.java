package com.example.GroupService.service;

import com.example.GroupService.dto.CreateGroupRequest;
import com.example.GroupService.dto.GroupResponse;
import com.example.GroupService.entity.Group;
import com.example.GroupService.entity.GroupMember;
import com.example.GroupService.repository.GroupMemberRepository;
import com.example.GroupService.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepo;
    private final GroupMemberRepository memberRepo;

    @Cacheable(value = "groups", key = "#page + '-' + #size")
    public Page<GroupResponse> getAllGroups(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return groupRepo.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Cacheable(value = "group", key = "#groupId")
    public GroupResponse getGroup(Long groupId) {
        Group group = groupRepo.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        return mapToResponse(group);
    }

    @CacheEvict(value = {"groups", "userGroups"}, allEntries = true)
    public GroupResponse createGroup(CreateGroupRequest request) {


        Group group = Group.builder()
                .name(request.getName())
                .createdBy(request.getCreatedBy())
                .build();

        Group savedGroup = groupRepo.save(group);

        // Add creator as member
        GroupMember member = GroupMember.builder()
                .groupId(savedGroup.getId())
                .userId(request.getCreatedBy())
                .build();

        memberRepo.save(member);

        return mapToResponse(savedGroup);
    }

    @CacheEvict(value = {"group", "groups", "userGroups"}, allEntries = true)
    public void addMember(Long groupId, String userId) {
        if (memberRepo.existsByGroupIdAndUserId(groupId, userId)) {
            throw new RuntimeException("User already in group");
        }
        memberRepo.save(new GroupMember(null, groupId, userId));
    }

    @CacheEvict(value = {"group", "groups"}, allEntries = true)
    public void removeMember(Long groupId, String userId) {
        memberRepo.deleteByGroupIdAndUserId(groupId, userId);
    }

    @Cacheable(value = "userGroups", key = "#userId + '-' + #page + '-' + #size")
    public Page<GroupResponse> getUserGroups(String userId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Group> groups = groupRepo.findGroupsByUserId(userId, pageable);

        return groups.map(this::mapToResponse);
    }


    private GroupResponse mapToResponse(Group group) {
        List<String> members = memberRepo.findByGroupId(group.getId())
                .stream()
                .map(GroupMember::getUserId)
                .toList();

        return GroupResponse.builder()
                .id(group.getId())
                .name(group.getName())
                .createdBy(group.getCreatedBy())
                .members(members)
                .build();
    }
}