package com.example.TaskManager;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Tasks")
@Entity
public class EntityTask {
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
    LocalDate deadlineDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    TaskPriority priority;
}
