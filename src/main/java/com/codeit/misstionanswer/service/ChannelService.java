package com.codeit.misstionanswer.service;

import com.codeit.misstionanswer.entity.*;

import java.util.*;

public interface ChannelService {

    Channel create(ChannelType type, String name, String description);

    Channel find(UUID channelId);

    List<Channel> findAll();

    Channel update(UUID channelId, String newName, String newDescription);

    void delete(UUID id);

}
