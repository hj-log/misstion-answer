package com.codeit.misstionanswer.repository;

import com.codeit.misstionanswer.entity.*;

import java.util.*;

public interface UserRepository {

    User save(User user);
    Optional<User> findById(UUID id);
    List<User> findAll();
    boolean existsById(UUID id);
    void deleteById(UUID id);
}
