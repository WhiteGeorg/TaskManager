package com.example.TaskManager.Task;

import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Task mapEntityToDomain(TaskEntity entityTask) {

        return new Task(
                entityTask.getId(),
                entityTask.getCreatorId(),
                entityTask.getAssignedId(),
                entityTask.getStatus(),
                entityTask.getCreateDateTime(),
                entityTask.getDeadlineDate(),
                entityTask.getCompleteTime(),
                entityTask.getPriority());
    }

    public TaskEntity mapDomainToEntity(Task task) {
        return new TaskEntity(
                task.getId(),
                task.getCreatorId(),
                task.getAssignedId(),
                task.getStatus(),
                task.getCreateDateTime(),
                task.getDeadlineDate(),
                task.getCompleteTime(),
                task.getPriority());
    }
}
