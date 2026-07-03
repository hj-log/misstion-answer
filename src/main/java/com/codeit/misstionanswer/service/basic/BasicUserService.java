package com.codeit.misstionanswer.service.basic;

import com.codeit.misstionanswer.entity.*;
import com.codeit.misstionanswer.repository.*;
import com.codeit.misstionanswer.service.*;

import java.util.*;

public class BasicUserService implements UserService {
    private final UserRepository userRepository;

    public BasicUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(String username, String email, String password) {
        User user = new User(username, email, password);
        return userRepository.save(user);
    }

    @Override
    public User find(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new NoSuchElementException("User with id " + userId + "not found"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(UUID userId, String newUsername, String newEmail, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new NoSuchElementException("User with id " + userId + "not found"));
        user.update(newUsername, newEmail, newPassword);
        return userRepository.save(user);
    }

    @Override
    public void delete(UUID id) {
        if(!userRepository.existsById(id)) {
            throw new NoSuchElementException("User with id " + id + "not found");
        }
        userRepository.deleteById(id);
    }
}
