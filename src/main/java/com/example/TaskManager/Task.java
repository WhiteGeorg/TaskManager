package com.example.TaskManager;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
    Long id;
    Long creatorId;
    Long assignedId;
    taskStatus status;
    LocalDateTime createDateTime;
    LocalDateTime deadlineDate;
    taskPriority priority;
}
