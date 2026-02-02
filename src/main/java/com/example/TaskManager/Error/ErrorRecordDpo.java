package com.example.TaskManager.Error;

import java.time.LocalDateTime;

public record ErrorRecordDpo(
        String message,
        String detailedMessage,
        LocalDateTime time
) {
}
