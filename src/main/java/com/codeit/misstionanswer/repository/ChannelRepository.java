package com.codeit.misstionanswer.repository;

import com.codeit.misstionanswer.entity.*;

import java.util.*;

public interface ChannelRepository {
    Channel save (Channel channel);
    Optional<Channel> findById(UUID id);
    List<Channel> findAll();
    boolean existsById(UUID id);
    void deleteById(UUID id);
}
