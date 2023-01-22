package com.example.task_management_system_ampada.controllers;

import com.example.task_management_system_ampada.exceptions.UserNotFoundException;
import com.example.task_management_system_ampada.models.User;
import com.example.task_management_system_ampada.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}")
    public Optional<User> findUserById(@PathVariable String id) {
        return userService.findUserById(id);
    }

    @GetMapping(path = "/")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping(path = "/login")
    public User loginUser(@RequestBody String username, @RequestBody String password) {
        return userService.loginUser(username, password);
    }

    @PostMapping(path = "/signup")
    public User signupUser(@RequestBody String username, @RequestBody String password) {
        return userService.signupUser(username, password);
    }

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUserById(@PathVariable String id) {
        userService.deleteUserById(id);
    }
}
