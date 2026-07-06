package com.codeit.misstionanswer.dto.request;

import java.time.*;
import java.util.*;

public record ReadStatusCreateRequest(
        UUID userId,
        UUID channelId,
        Instant lastReadAt
) {
}
