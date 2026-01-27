package com.example.TaskManager;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ServiceTaskManager {
    HashMap<Long, Task> mapTasks;
    AtomicLong idCounter;
    ServiceTaskManager() {
        mapTasks = new HashMap<Long, Task>();
        idCounter = new AtomicLong();
    }

    public Task postNewTask(Task task) {
        if (task.getId() != null)
            throw new IllegalArgumentException("Permission denied");
        var newTask = new Task(
                idCounter.incrementAndGet(),
                task.getCreatorId(),
                task.getAssignedId(),
                task.getStatus(),
                task.getCreateDateTime(),
                task.getDeadlineDate(),
                task.getPriority());
        mapTasks.put(newTask.getId(),newTask);

        return newTask;
    }

    public Task getTaskById(Long id) {
        if (!mapTasks.containsKey(id)) {
            throw new NoSuchElementException("Can not find any tasks with id " + id);
        }
        return mapTasks.get(id);
    }

    public List<Task> getTaskList() {
        return mapTasks.values().stream().toList();
    }

    public void deleteTaskById(Long id) {
        if (!mapTasks.containsKey(id)) {
            throw new NoSuchElementException("Can not find any tasks with id " + id);
        }
        mapTasks.remove(id);
    }

    public Task putTaskById(Long id, Task task) {

        if (!task.getId().equals(id))
            throw new IllegalArgumentException("ID are Different");
        if (!mapTasks.containsKey(id))
            throw new NoSuchElementException("Can not find any tasks with id " + id);
        var newTask = new Task(
                task.getId(),
                task.getCreatorId(),
                task.getAssignedId(),
                task.getStatus(),
                task.getCreateDateTime(),
                task.getDeadlineDate(),
                task.getPriority());
        mapTasks.put(newTask.getId(),newTask);
        return newTask;
    }
}
