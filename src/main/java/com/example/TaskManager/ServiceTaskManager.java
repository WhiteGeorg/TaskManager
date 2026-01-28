package com.example.TaskManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ServiceTaskManager {

    RepositoryTask repositoryTask;

    @Autowired
    ServiceTaskManager(RepositoryTask repositoryTask) {
        this.repositoryTask = repositoryTask;
    }

    public Task postNewTask(Task task) {
        if (task.getId() != null)
            throw new IllegalArgumentException("Permission denied,id should be null");
        if (task.getStatus() != null)
            throw new IllegalArgumentException("Permission denied,Status should be null");

        var newTask = new EntityTask(
                null,
                task.getCreatorId(),
                task.getAssignedId(),
                TaskStatus.CREATED,
                task.getCreateDateTime(),
                task.getDeadlineDate(),
                task.getPriority());
        EntityTask newEntity = repositoryTask.save(newTask);
        return mapEntityToDomain(newEntity);
    }

    public Task getTaskById(Long id) {
        EntityTask entityTask = repositoryTask
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can not find any tasks with id " + id));

        return mapEntityToDomain(entityTask);
    }

    public List<Task> getTaskList() {

        return repositoryTask
                .findAll()
                .stream()
                .map(this::mapEntityToDomain)
                .toList();
    }

    public void deleteTaskById(Long id) {

        repositoryTask
                .findById(id)
                .orElseThrow(()-> new NoSuchElementException("Can not find any tasks with id " + id));

        repositoryTask.deleteById(id);
    }

    public Task putTaskById(Long id, Task task) {
        var taskToUpdate = repositoryTask
                .findById(id)
                .orElseThrow(()-> new NoSuchElementException("Can not find any tasks with id " + id));

        if (task.getId() != null)
            throw new IllegalArgumentException("Permission denied,id should be null");

        if (taskToUpdate
                .getStatus()
                .equals(TaskStatus.DONE))
            throw new IllegalArgumentException("PERMISSION DENIED task already DONE");

        var newTask = new EntityTask(
                taskToUpdate.getId(),
                task.getCreatorId(),
                task.getAssignedId(),
                task.getStatus(),
                task.getCreateDateTime(),
                task.getDeadlineDate(),
                task.getPriority());

        repositoryTask.save(newTask);
        return mapEntityToDomain(newTask);
    }

    public Task reopenTaskById(Long id) {

        var entityToUpdate = repositoryTask
                .findById(id)
                .orElseThrow(()-> new NoSuchElementException("Can not find any tasks with id " + id));

        if (!entityToUpdate
                .getStatus()
                .equals(TaskStatus.DONE))
            throw new IllegalArgumentException("Task already available");

        entityToUpdate.setStatus(TaskStatus.IN_PROGRESS);

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
                entityTask.getPriority());
    }


}
