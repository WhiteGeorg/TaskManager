package com.example.TaskManager.Task;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskManagerControllerTest {
    @Mock
    TaskManagerService taskManagerService;

    @InjectMocks
    TaskManagerController taskManagerController;

    @Test
    void getTaskByIdTest() {
        Long id = 1L;
        Task task = new Task(
                id,
                id,
                null,
                TaskStatus.CREATED,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null,
                TaskPriority.LOW);
        when(taskManagerService.getTaskById(id)).thenReturn(task);

        assertEquals(ResponseEntity.ok(task),taskManagerController.getTaskById(id));
    }
}