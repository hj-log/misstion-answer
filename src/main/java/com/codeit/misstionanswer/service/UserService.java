package com.codeit.misstionanswer.service;

import com.codeit.misstionanswer.entity.*;

import java.util.*;

public interface UserService {

    User create(String username, String email, String password);

    User find(UUID id);

    List<User> findAll();

    User update(UUID id, String newUsername, String newEmail, String newPassword);

    void delete(UUID id);
}
