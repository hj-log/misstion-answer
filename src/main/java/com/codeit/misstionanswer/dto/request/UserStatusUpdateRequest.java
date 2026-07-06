package com.codeit.misstionanswer.dto.request;

import java.time.*;

public record UserStatusUpdateRequest(
        Instant newLastActiveAt
) {
}
