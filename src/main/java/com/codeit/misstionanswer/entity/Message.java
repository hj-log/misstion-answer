package com.codeit.misstionanswer.entity;

import java.time.*;
import java.util.*;

public class Message {
    private UUID id;
    private Long createdAt;
    private Long updatedAt;

    private String content;

    private UUID channelId;
    private UUID authorId;

    public Message(String content, UUID channelId, UUID authorId) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now().getEpochSecond();

        this.content = content;
        this.channelId = channelId;
        this.authorId = authorId;
    }

    public UUID getId(){
        return id;
    }

    public Long createdAt(){
        return createdAt;
    }

    public Long updatedAt(){
        return updatedAt;
    }

    public String getContent(){
        return content;
    }

    public UUID getChannelId(){
        return channelId;
    }

    public UUID getAuthorId(){
        return authorId;
    }

    public void update(String content) {
        boolean anyValueupdated = false;

        if (content != null && !content.equals(this.content)) {
            this.content = content;
            anyValueupdated = true;
        }

        if (anyValueupdated) {
            this.updatedAt = Instant.now().getEpochSecond();
        }
    }
}
