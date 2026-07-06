package com.codeit.misstionanswer.dto.request;

import java.util.*;

public record MessageCreateRequest(
        String content,
        UUID channelId,
        UUID authorId
) {
}
