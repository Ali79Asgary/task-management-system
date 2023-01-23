package com.example.task_management_system_ampada.controllers;

import com.example.task_management_system_ampada.models.User;
import com.example.task_management_system_ampada.services.JWTService;
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

    private final JWTService jwtService;

    @Autowired
    public UserController(UserService userService, JWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> loginUser(@RequestBody User data) {
        String token = userService.loginUser(data);
        return ResponseEntity.ok(token);
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<String> signupUser(@RequestBody User data) {
        String result = userService.signupUser(data);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/{id}")
    public Optional<User> findUserById(@PathVariable String id) {
        return userService.findUserById(id);
    }

    @GetMapping(path = "/")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
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
