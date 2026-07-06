package com.codeit.misstionanswer.dto.request;

import java.time.*;

public record ReadStatusUpdateRequest(
        Instant newLastReadAt
) {
}
