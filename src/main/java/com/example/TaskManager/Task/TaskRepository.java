package com.example.TaskManager.Task;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity,Long> {
    @Query("""
            SELECT t
            FROM TaskEntity t
            WHERE
                (:creatorId IS NULL OR t.creatorId = :creatorId)
                AND (:assignedId IS NULL OR t.assignedId = :assignedId)
                AND (:status IS NULL OR t.status = :status)
            """)
    List<TaskEntity> findAllByFilter(
            @Param("creatorId") Long creatorId,
            @Param("assignedId") Long assignedId,
            @Param("status") TaskStatus status,
            Pageable pageable
    );
}
