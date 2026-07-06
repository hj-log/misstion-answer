package com.codeit.misstionanswer.repository;

import com.codeit.misstionanswer.entity.*;

import java.util.*;

public interface MessageRepository {

    Message save(Message message);
    Optional<Message> findById(UUID id);
    List<Message> findAllByChannelId(UUID channelId);
    boolean existsById(UUID id);
    void deleteById(UUID id);
    void deleteAllByChannelId(UUID channelId);
}
