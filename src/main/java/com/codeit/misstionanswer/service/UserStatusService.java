package com.codeit.misstionanswer.service;

import com.codeit.misstionanswer.dto.request.*;
import com.codeit.misstionanswer.entity.*;

import java.util.*;

public interface UserStatusService {

    UserStatus create(UserStatusCreateRequest request);

    UserStatus find(UUID userStatusId);

    List<UserStatus> findAll();

    UserStatus update(UUID userStatusId, UserStatusUpdateRequest request);

    UserStatus updateByUserId(UUID userId, UserStatusUpdateRequest request);

    void delete(UUID userStatusId);

}

