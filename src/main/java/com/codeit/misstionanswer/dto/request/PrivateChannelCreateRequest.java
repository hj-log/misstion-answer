package com.codeit.misstionanswer.dto.request;

import java.util.*;

public record PrivateChannelCreateRequest(
        List<UUID> participantIds
) {
}
