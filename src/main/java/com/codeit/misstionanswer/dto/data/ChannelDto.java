package com.codeit.misstionanswer.dto.data;

import com.codeit.misstionanswer.entity.*;

import java.time.*;
import java.util.*;

public record ChannelDto(
        UUID id,
        ChannelType type,
        String name,
        String description,
        List<UUID> participants,
        Instant lastMessageAt
        ) {

}
