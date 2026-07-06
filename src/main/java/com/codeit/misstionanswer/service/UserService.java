package com.codeit.misstionanswer.service;

import com.codeit.misstionanswer.dto.data.*;
import com.codeit.misstionanswer.dto.request.*;
import com.codeit.misstionanswer.entity.*;

import java.util.*;

public interface UserService {

    User create(UserCreateRequest userCreateRequest,
                Optional<BinaryContentCreateRequest> profileCreateRequest);

    UserDto find(UUID userId);

    List<UserDto> findAll();

    User update(UUID userId, UserUpdateRequest userUpdateRequest, Optional<BinaryContentCreateRequest> profileCreateRequest);

    void delete(UUID userId);
}
