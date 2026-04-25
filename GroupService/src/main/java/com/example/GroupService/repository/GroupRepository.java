package com.example.GroupService.repository;

import com.example.GroupService.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Page<Group> findAll(Pageable pageable);

    @Query("""
    SELECT g FROM Group g 
    JOIN GroupMember gm ON g.id = gm.groupId 
    WHERE gm.userId = :userId
    """)
    Page<Group> findGroupsByUserId(String userId, Pageable pageable);
}
