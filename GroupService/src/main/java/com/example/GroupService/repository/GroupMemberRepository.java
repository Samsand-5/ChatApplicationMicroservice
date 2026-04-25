package com.example.GroupService.repository;

import com.example.GroupService.entity.Group;
import com.example.GroupService.entity.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

    List<GroupMember> findByGroupId(Long groupId);

    List<GroupMember> findByUserId(String userId);

    void deleteByGroupIdAndUserId(Long groupId, String userId);

    boolean existsByGroupIdAndUserId(Long groupId, String userId);

}