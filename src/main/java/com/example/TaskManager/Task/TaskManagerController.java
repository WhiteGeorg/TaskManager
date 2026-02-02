package com.example.TaskManager.Task;


import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskManagerController {
    Logger log = LoggerFactory.getLogger(TaskManagerController.class);

    TaskManagerService serviceTaskManager;

    @Autowired
    public TaskManagerController(TaskManagerService serviceTaskManager) {
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
    public ResponseEntity<Task> postNewTask(@RequestBody @Valid Task task) {
        log.info("postNewTask::POST request with body:{}",task.toString());
        var newTask = serviceTaskManager.postNewTask(task);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        log.info("deleteTaskById::DELETE request by id:{}", id);
        serviceTaskManager.deleteTaskById(id);
        return ResponseEntity
                .ok()
                .build();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Task> putTaskById(@PathVariable Long id, @RequestBody @Valid Task task) {
//        log.info("putTaskById::PUT request with body:{}",task.toString());
//        var taskToUpdate = serviceTaskManager.putTaskById(id, task);
//
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(taskToUpdate);
//    }

    @PatchMapping("/{id}/start")
    public ResponseEntity<Task> startTaskById(@PathVariable Long id) {
        log.info("startTaskById::PATCH request to start task by id:{}", id);
        var taskToUpdate = serviceTaskManager.startTaskById(id);

        return ResponseEntity.ok(taskToUpdate);
    }
    @PatchMapping("/{id}/reopen")
    public ResponseEntity<Task> reopenTaskById(@PathVariable Long id, @RequestBody @Valid Task task) {
        log.info("reopenTaskById::PATCH request to reopen task by id:{}", id);
        var taskToUpdate = serviceTaskManager.reopenTaskById(id,task);

        return ResponseEntity.ok(taskToUpdate);
    }
    @PatchMapping("/{id}/complete")
    public ResponseEntity<Task> completeTaskById(@PathVariable Long id) {
        log.info("completeTaskById::PATCH request to complete task by id:{}", id);
        var taskToUpdate = serviceTaskManager.completeTaskById(id);

        return ResponseEntity.ok(taskToUpdate);
    }


}

