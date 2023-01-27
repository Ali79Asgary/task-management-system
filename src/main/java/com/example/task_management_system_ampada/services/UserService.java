package com.example.task_management_system_ampada.services;

import com.example.task_management_system_ampada.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserById(String id);

    User findUserByUsername(String username);

    List<User> findAllUsers();

    User saveUser(User user);

    User updateUser(String id, User newUser);

    void deleteUserById(String id);

}
