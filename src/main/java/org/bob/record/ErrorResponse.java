package org.bob.record;

import java.time.OffsetDateTime;

public record ErrorResponse(
        OffsetDateTime offsetDateTime,
        int status,
        String error,
        String message,
        String path
) {
}
