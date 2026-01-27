package com.example.TaskManager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Task> getTaskList(){
        log.info("getTaskList GET request called");
        return serviceTaskManager.getTaskList();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable("id") Long id){
        log.info("getTaskById GET request by id {} called",id);
        return serviceTaskManager.getTaskById(id);
    }

    @PostMapping
    public ResponseEntity<Task> postNewTask(@RequestBody Task task)
    {
        var newTask = serviceTaskManager.postNewTask(task);
        return ResponseEntity.ok(newTask);
    }



















}

