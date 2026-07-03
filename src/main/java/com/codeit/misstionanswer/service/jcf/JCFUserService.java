package com.codeit.misstionanswer.service.jcf;

import com.codeit.misstionanswer.entity.*;
import com.codeit.misstionanswer.service.*;

import java.util.*;

public class JCFUserService implements UserService {

    private final Map<UUID, User> data;

    public JCFUserService() {
        this.data = new HashMap<>();
    }

    @Override
    public User create(String username, String email, String password) {
        User user = new User(username, email, password);
        this.data.put(user.getId(), user);
        return user;
    }

    @Override
    public User find(UUID id) {
        User userNullable = this.data.get(id);

        return Optional.ofNullable(userNullable)
                .orElseThrow(() -> new NoSuchElementException("user with id " + id + " not found "));
    }

    @Override
    public List<User> findAll() {
        return this.data.values().stream().toList();
    }

    @Override
    public User update(UUID id, String newUsername, String newEmail, String newPassword) {
        User userNullable = this.data.get(id);
        User user = Optional.ofNullable(userNullable)
                .orElseThrow(() -> new NoSuchElementException("user with id " + id + " not found " ));
        user.update(newUsername, newEmail, newPassword);
        return user;
    }

    @Override
    public void delete(UUID id) {

        if(!this.data.containsKey(id)) {
            throw new NoSuchElementException("user with id " + id + " not found ");
        }
        this.data.remove(id);
    }
}
