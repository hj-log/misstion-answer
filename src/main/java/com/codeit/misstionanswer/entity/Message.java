package com.codeit.misstionanswer.entity;

import lombok.*;

import java.io.*;
import java.time.*;
import java.util.*;

@Getter
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;

    private String content;

    private UUID channelId;
    private UUID authorId;
    private List<UUID> attachmentIds;

    public Message(String content, UUID channelId, UUID authorId, List<UUID> attachmentIds) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();

        this.content = content;
        this.channelId = channelId;
        this.authorId = authorId;
        this.attachmentIds = attachmentIds;
    }

    public void update(String content) {
        boolean anyValueupdated = false;

        if (content != null && !content.equals(this.content)) {
            this.content = content;
            anyValueupdated = true;
        }

        if (anyValueupdated) {
            this.updatedAt = Instant.now();
        }
    }
}
