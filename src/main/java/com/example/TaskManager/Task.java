package com.example.TaskManager;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Task {
    @Null
    Long id;
    @NotNull
    Long creatorId;
    @Null
    Long assignedId;
    TaskStatus status;

    @Null
    LocalDateTime createDateTime;
    @NotNull
    @Future
    LocalDateTime deadlineDate;

    @Null
    LocalDateTime completeTime;

    @NotNull
    TaskPriority priority;
}
