package com.codeit.misstionanswer.service;

import com.codeit.misstionanswer.dto.data.*;
import com.codeit.misstionanswer.dto.request.*;
import com.codeit.misstionanswer.entity.*;

import java.util.*;

public interface ChannelService {

    Channel create(PublicChannelCreateRequest request);

    Channel create(PrivateChannelCreateRequest request);

    ChannelDto find(UUID channelId);

    List<ChannelDto> findAllByUserId(UUID userId);

    Channel update(UUID channelId, PublicChannelUpdateRequest request);

    void delete(UUID channelId);

}
