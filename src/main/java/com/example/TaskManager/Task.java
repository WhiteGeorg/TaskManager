package com.example.TaskManager;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
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
    @NotNull
    @FutureOrPresent
    LocalDateTime createDateTime;
    @NotNull
    @FutureOrPresent
    LocalDateTime deadlineDate;
    @NotNull
    TaskPriority priority;
}
