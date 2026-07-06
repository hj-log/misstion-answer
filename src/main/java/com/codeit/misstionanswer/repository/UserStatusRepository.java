package com.codeit.misstionanswer.repository;

import com.codeit.misstionanswer.entity.*;

import java.util.*;

public interface UserStatusRepository {

    UserStatus save(UserStatus userStatus);
    Optional<UserStatus> findById(UUID id);
    Optional<UserStatus> findByUserId(UUID userId);
    List<UserStatus> findAll();
    boolean existsById(UUID id);
    void deleteById(UUID id);
    void deleteByUserId(UUID userId);
}
