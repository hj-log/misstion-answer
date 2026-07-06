package com.codeit.misstionanswer.service;

import com.codeit.misstionanswer.dto.request.*;
import com.codeit.misstionanswer.entity.*;

import java.util.*;

public interface MessageService {

    Message create(MessageCreateRequest messageCreateRequest,
                   List<BinaryContentCreateRequest> binaryContentCreateRequests);

    Message find(UUID messageId);

    List<Message> findAllByChannelId(UUID channelId);

    Message update(UUID messageId, MessageUpdateRequest request);

    void delete(UUID messageId);
}
