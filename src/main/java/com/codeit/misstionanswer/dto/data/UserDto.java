package com.codeit.misstionanswer.dto.data;

import java.time.*;
import java.util.*;

public record UserDto(
        UUID id,
        Instant createdAt,
        Instant updatedAt,
        String username,
        String email,
        UUID profileId,
        Boolean online
) {
}
