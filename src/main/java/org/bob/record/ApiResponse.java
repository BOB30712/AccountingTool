package org.bob.record;

import java.time.LocalDateTime;

public record ApiResponse<T>(
        String status,
        T data,
        String message,
        LocalDateTime timestamp
) {
}
