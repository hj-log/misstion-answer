package com.codeit.misstionanswer.service;

import com.codeit.misstionanswer.entity.*;

import java.util.*;

public interface MessageService {

    Message create(String content, UUID channelId, UUID authorId);

    Message find(UUID messageId);

    List<Message> findAll();

    Message update(UUID messageId, String newContent);

    void delete(UUID messageId);
}
