package com.example.TaskManager;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ServiceTaskManager {
    HashMap<Long, Task> mapTasks;

    ServiceTaskManager() {
        mapTasks = new HashMap<Long, Task>();
        mapTasks.put(0L,
                new Task(
                        0L,
                        0L,
                        1L,
                        TaskStatus.CREATED,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        TaskPriority.LOW
                ));
        mapTasks.put(1L,
                new Task(
                        1L,
                        0L,
                        1L,
                        TaskStatus.CREATED,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        TaskPriority.MEDIUM
                ));
        mapTasks.put(2L,
                new Task(
                        2L,
                        1L,
                        0L,
                        TaskStatus.CREATED,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        TaskPriority.HIGH
                ));
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

}
