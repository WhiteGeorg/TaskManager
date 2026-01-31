package com.example.TaskManager;


import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceTaskManager {

    RepositoryTask repositoryTask;

    @Autowired
    ServiceTaskManager(RepositoryTask repositoryTask) {
        this.repositoryTask = repositoryTask;
    }

    public Task postNewTask(Task task) {

        if (task.getStatus() != null)
            throw new IllegalStateException("Status should be null");
        if (task.getCompleteTime() != null)
            throw new IllegalArgumentException("Complete time should be null");

        var newTask = new EntityTask(
                null,
                task.getCreatorId(),
                task.getAssignedId(),
                TaskStatus.CREATED,
                LocalDateTime.now(),
                task.getDeadlineDate(),
                null,
                task.getPriority());
        EntityTask newEntity = repositoryTask.save(newTask);
        return mapEntityToDomain(newEntity);
    }

    public Task getTaskById(Long id) {
        EntityTask entityTask = repositoryTask
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can not find any tasks with id " + id));

        return mapEntityToDomain(entityTask);
    }

    public List<Task> getTaskList() {

        return repositoryTask
                .findAll()
                .stream()
                .map(this::mapEntityToDomain)
                .sorted((a,b) -> {return Long.compare(a.getId(),b.getId());} )
                .toList();
    }

    public void deleteTaskById(Long id) {

        repositoryTask
                .findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Can not find any tasks with id " + id));

        repositoryTask.deleteById(id);
    }

    public Task putTaskById(Long id, Task task) {
        var taskToUpdate = repositoryTask
                .findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Can not find any tasks with id " + id));

        if (taskToUpdate
                .getStatus()
                .equals(TaskStatus.DONE))
            throw new IllegalArgumentException("PERMISSION DENIED task already DONE");

        if (task.getCompleteTime() != null)
            throw new IllegalArgumentException("Complete time should be null");

        var newTask = new EntityTask(
                taskToUpdate.getId(),
                task.getCreatorId(),
                task.getAssignedId(),
                task.getStatus(),
                LocalDateTime.now(),
                task.getDeadlineDate(),
                null,
                task.getPriority());

        repositoryTask.save(newTask);
        return mapEntityToDomain(newTask);
    }

    public Task reopenTaskById(Long id,Task task) {

        var entityToUpdate = repositoryTask
                .findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Can not find any tasks with id " + id));
        if (!entityToUpdate
                .getStatus()
                .equals(TaskStatus.DONE))
            throw new IllegalStateException("Task already available");

        entityToUpdate.setStatus(TaskStatus.CREATED);
        entityToUpdate.setDeadlineDate(task.getDeadlineDate());
        entityToUpdate.setAssignedId(null);
        entityToUpdate.setCompleteTime(null);
        entityToUpdate.setPriority(task.getPriority());

        repositoryTask.save(entityToUpdate);

        return mapEntityToDomain(entityToUpdate);
    }
    public Task startTaskById(Long id) {

        var entityToUpdate = repositoryTask
                .findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Can not find any tasks with id " + id));

        if (!entityToUpdate
                .getStatus()
                .equals(TaskStatus.CREATED))
            throw new IllegalStateException("Can not open not CREATED task");

        entityToUpdate.setStatus(TaskStatus.IN_PROGRESS);
        entityToUpdate.setAssignedId(id);

        repositoryTask.save(entityToUpdate);

        return mapEntityToDomain(entityToUpdate);
    }
    public Task completeTaskById(Long id) {

        var entityToUpdate = repositoryTask
                .findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Can not find any tasks with id " + id));

        if (!entityToUpdate
                .getStatus()
                .equals(TaskStatus.IN_PROGRESS))
            throw new IllegalStateException("Can not complete not IN_PROGRESS task");

        entityToUpdate.setStatus(TaskStatus.DONE);
        entityToUpdate.setCompleteTime(LocalDateTime.now());

        repositoryTask.save(entityToUpdate);

        return mapEntityToDomain(entityToUpdate);
    }

    private Task mapEntityToDomain(EntityTask entityTask) {
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
}
