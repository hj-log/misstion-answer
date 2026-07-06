package com.codeit.misstionanswer.service;

import com.codeit.misstionanswer.dto.request.*;
import com.codeit.misstionanswer.entity.*;

import java.util.*;

public interface ReadStatusService {

    ReadStatus create (ReadStatusCreateRequest request);
    ReadStatus find(UUID readStatusId);
    List<ReadStatus> findAllByUserId(UUID userId);
    ReadStatus update(UUID readStatus, ReadStatusUpdateRequest request);
    void delete(UUID readStatusId);
}
