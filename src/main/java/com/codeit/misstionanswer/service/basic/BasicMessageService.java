package com.codeit.misstionanswer.service.basic;

import com.codeit.misstionanswer.entity.*;
import com.codeit.misstionanswer.repository.*;
import com.codeit.misstionanswer.service.*;

import java.util.*;

public class BasicMessageService implements MessageService {

    private final MessageRepository messageRepository;
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;

    public BasicMessageService(MessageRepository messageRepository, ChannelRepository channelRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.channelRepository = channelRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Message create(String content, UUID channelId, UUID authorId) {
        if (!userRepository.existsById(authorId)) {
            throw new NoSuchElementException("user with id " + authorId + " not found");
        }
        if (!channelRepository.existsById(channelId)) {
            throw new NoSuchElementException("channel with id " + channelId + " not found");
        }
        Message message = new Message(content, channelId, authorId);
        return messageRepository.save(message);
    }

    @Override
    public Message find(UUID messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new NoSuchElementException("message with id " + messageId + " not found"));
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message update(UUID messageId, String newContent) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new NoSuchElementException("message with id " + messageId + " not found"));

        message.update(newContent);
        return messageRepository.save(message);
    }

    @Override
    public void delete(UUID messageId) {
        if(!messageRepository.existsById(messageId)) {
            throw new NoSuchElementException("message with id " + messageId + " not found ");
        }
        messageRepository.deleteById(messageId);
    }
}
