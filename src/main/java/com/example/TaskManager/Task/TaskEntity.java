package com.example.TaskManager.Task;


import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Tasks")
@Entity
public class TaskEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "creator_Id")
    Long creatorId;

    @Column(name = "assigned_Id")
    Long assignedId;

    @Column(name = "status")
    TaskStatus status;

    @Column(name = "create_DateTime")
    LocalDateTime createDateTime;

    @Column(name = "deadline_Date")
    LocalDateTime deadlineDate;

    @Null
    @Column(name = "complete_Time")
    LocalDateTime completeTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    TaskPriority priority;
}
