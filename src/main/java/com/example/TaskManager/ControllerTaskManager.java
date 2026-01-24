package com.example.TaskManager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerTaskManager {
    Logger log = LoggerFactory.getLogger(ControllerTaskManager.class);

    ServiceTaskManager serviceTaskManager;

    @Autowired
    public ControllerTaskManager(ServiceTaskManager serviceTaskManager) {
        this.serviceTaskManager = serviceTaskManager;
    }

    @GetMapping("/tasks")
    public List<Task> getTaskList(){
        log.info("getTaskList GET request called");
        return serviceTaskManager.getTaskList();
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable("id") Long id){
        log.info("getTaskById GET request by id {} called",id);
            return serviceTaskManager.getTaskById(id);
    }
}
