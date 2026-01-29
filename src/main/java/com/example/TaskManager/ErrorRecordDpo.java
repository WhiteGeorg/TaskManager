package com.example.TaskManager;

import java.time.LocalDateTime;

public record ErrorRecordDpo(
        String message,
        String detailedMessage,
        LocalDateTime time
) {
}
