package com.example.TaskManager;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Task {
    Long id;
    Long creatorId;
    Long assignedId;
    TaskStatus status;
    LocalDateTime createDateTime;
    LocalDateTime deadlineDate;
    TaskPriority priority;
}
