package com.bugtracker.bugtracker.repository;

import com.bugtracker.bugtracker.entity.Bug;
import com.bugtracker.bugtracker.entity.Status;
import com.bugtracker.bugtracker.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BugRepository extends JpaRepository<Bug, Long> {

    // 🔹 FILTER METHODS
    List<Bug> findByStatus(Status status);

    List<Bug> findByPriority(Priority priority);

    List<Bug> findByStatusAndPriority(Status status, Priority priority);

    // PAGINATION
    Page<Bug> findAll(Pageable pageable);
}