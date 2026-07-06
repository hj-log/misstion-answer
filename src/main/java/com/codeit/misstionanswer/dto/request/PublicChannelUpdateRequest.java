package com.codeit.misstionanswer.dto.request;

public record PublicChannelUpdateRequest(
        String newName,
        String newDescription
) {
}
