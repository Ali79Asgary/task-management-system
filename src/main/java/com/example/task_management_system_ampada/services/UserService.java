package com.example.task_management_system_ampada.services;

import com.example.task_management_system_ampada.models.User;

import java.util.List;

public interface UserService {

    User findUserById(String id);

    List<User> findAllUsers();

    User saveUser(User user);

    User updateUser(String id, User newUser);

    void deleteUserById(String id);

}
