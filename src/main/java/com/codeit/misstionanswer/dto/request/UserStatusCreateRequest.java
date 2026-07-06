package com.codeit.misstionanswer.dto.request;

import java.time.*;
import java.util.*;

public record UserStatusCreateRequest(
        UUID userId,
        Instant lastActiveAt
) {
}
