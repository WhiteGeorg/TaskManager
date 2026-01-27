package com.example.TaskManager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class ControllerTaskManager {
    Logger log = LoggerFactory.getLogger(ControllerTaskManager.class);

    ServiceTaskManager serviceTaskManager;

    @Autowired
    public ControllerTaskManager(ServiceTaskManager serviceTaskManager) {
        this.serviceTaskManager = serviceTaskManager;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTaskList() {
        log.info("getTaskList::GET request called");
        var taskList = serviceTaskManager.getTaskList();
        return ResponseEntity.ok(taskList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id) {
        log.info("getTaskById::GET request by id {} called", id);
        var task = serviceTaskManager.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Task> postNewTask(@RequestBody Task task) {
        log.info("postNewTask::POST request with body:" + task.toString());
        var newTask = serviceTaskManager.postNewTask(task);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id)
    {
        log.info("deleteTaskById::DELETE request by id:{}",id);
        serviceTaskManager.deleteTaskById(id);
        return ResponseEntity
                .ok()
                .build();
    }


















}

