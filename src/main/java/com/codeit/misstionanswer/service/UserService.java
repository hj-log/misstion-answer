package com.codeit.misstionanswer.service;

import com.codeit.misstionanswer.entity.*;

import java.util.*;

public interface UserService {

    User create(String username, String email, String password);

    User find(UUID userId);

    List<User> findAll();

    User update(UUID userId, String newUsername, String newEmail, String newPassword);

    void delete(UUID id);
}
