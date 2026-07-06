package com.codeit.misstionanswer.service;

import com.codeit.misstionanswer.dto.request.*;
import com.codeit.misstionanswer.entity.*;

import java.util.*;

public interface BinaryContentService {

    BinaryContent create(BinaryContentCreateRequest request);
    BinaryContent find(UUID binaryContentId);
    List<BinaryContent> findAllByIdIn(List<UUID> binaryContentIds);
    void delete(UUID binaryContentId);
}
