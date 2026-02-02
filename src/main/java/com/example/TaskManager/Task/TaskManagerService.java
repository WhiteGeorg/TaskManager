package com.example.TaskManager.Task;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskManagerService {

    TaskRepository taskRepository;
    TaskMapper mapper;

    @Autowired
    TaskManagerService(TaskRepository taskRepository, TaskMapper mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    public Task postNewTask(Task task) {

        if (task.getStatus() != null)
            throw new IllegalStateException("Status should be null");

        var newTask = mapper.mapDomainToEntity(task);
        newTask.setStatus(TaskStatus.CREATED);
        newTask.setCreateDateTime(LocalDateTime.now());
        TaskEntity newEntity = taskRepository.save(newTask);

        return mapper.mapEntityToDomain(newEntity);
    }

    public Task getTaskById(Long id) {
        TaskEntity entityTask = taskRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can not find any tasks with id " + id));

        return mapper.mapEntityToDomain(entityTask);
    }

    public List<Task> getTaskList() {

        return taskRepository
                .findAll()
                .stream()
                .map(mapper::mapEntityToDomain)
                .sorted((a,b) -> {return Long.compare(a.getId(),b.getId());} )
                .toList();
    }

    public void deleteTaskById(Long id) {

        taskRepository
                .findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Can not find any tasks with id " + id));

        taskRepository.deleteById(id);
    }

//    public Task putTaskById(Long id, Task task) {
//        var taskToUpdate = taskRepository
//                .findById(id)
//                .orElseThrow(()-> new EntityNotFoundException("Can not find any tasks with id " + id));
//
//        if (taskToUpdate
//                .getStatus()
//                .equals(TaskStatus.DONE))
//            throw new IllegalStateException("PERMISSION DENIED task already DONE");
//
//        var newTask = mapper.mapDomainToEntity(task);
//        newTask.setCreateDateTime(LocalDateTime.now());
//        newTask.setId(taskToUpdate.getId());
//
//        taskRepository.save(newTask);
//        return mapper.mapEntityToDomain(newTask);
//    }

    public Task reopenTaskById(Long id,Task task) {

        var entityToUpdate = taskRepository
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

        taskRepository.save(entityToUpdate);

        return mapper.mapEntityToDomain(entityToUpdate);
    }
    //добавить параметр с assigned
    public Task startTaskById(Long id,Long assignedId) {

        var entityToUpdate = taskRepository
                .findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Can not find any tasks with id " + id));

        if (!entityToUpdate
                .getStatus()
                .equals(TaskStatus.CREATED))
            throw new IllegalStateException("Can not open not CREATED task");

        entityToUpdate.setStatus(TaskStatus.IN_PROGRESS);
        entityToUpdate.setAssignedId(id);

        taskRepository.save(entityToUpdate);

        return mapper.mapEntityToDomain(entityToUpdate);
    }
    public Task completeTaskById(Long id) {

        var entityToUpdate = taskRepository
                .findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Can not find any tasks with id " + id));

        if (!entityToUpdate
                .getStatus()
                .equals(TaskStatus.IN_PROGRESS))
            throw new IllegalStateException("Can not complete not IN_PROGRESS task");

        entityToUpdate.setStatus(TaskStatus.DONE);
        entityToUpdate.setCompleteTime(LocalDateTime.now());

        taskRepository.save(entityToUpdate);

        return mapper.mapEntityToDomain(entityToUpdate);
    }
}
