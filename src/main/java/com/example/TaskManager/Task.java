package com.example.TaskManager;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Task {
    Long id;
    Long creatorId;
    Long assignedId;
    TaskStatus status;
    LocalDateTime createDateTime;
    LocalDate deadlineDate;
    TaskPriority priority;
}
