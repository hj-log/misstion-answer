package com.codeit.misstionanswer.service.basic;

import com.codeit.misstionanswer.entity.*;
import com.codeit.misstionanswer.repository.*;
import com.codeit.misstionanswer.service.*;

import java.util.*;

public class BasicChannelService implements ChannelService {

    private final ChannelRepository channelRepository;
    public BasicChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    public Channel create(ChannelType type, String name, String description) {
        Channel channel = new Channel(type, name, description);
        return channelRepository.save(channel);
    }

    @Override
    public Channel find(UUID channelId) {
        return channelRepository.findById(channelId).orElseThrow(()->
                new NoSuchElementException("channel with id " + channelId + " not found "));
    }

    @Override
    public List<Channel> findAll() {
        return channelRepository.findAll();
    }

    @Override
    public Channel update(UUID channelId, String newName, String newDescription) {
        Channel channel = channelRepository.findById(channelId).orElseThrow(()->
                new NoSuchElementException("channel with id " + channelId + " not found "));
        channel.update(newName, newDescription);
        return channelRepository.save(channel);
    }

    @Override
    public void delete(UUID id) {
        if(!channelRepository.existsById(id)) {
            throw new NoSuchElementException("channel with id " + id + " not found ");
        }
        channelRepository.deleteById(id);
    }
}
