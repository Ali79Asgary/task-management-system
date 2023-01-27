package com.example.task_management_system_ampada.controllers;

import com.example.task_management_system_ampada.requests.LoginRequest;
import com.example.task_management_system_ampada.requests.SignUpRequest;
import com.example.task_management_system_ampada.responses.LoginResponse;
import com.example.task_management_system_ampada.responses.SignUpResponse;
import com.example.task_management_system_ampada.services.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl service;

    @PostMapping(path = "/signup/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SignUpResponse> register(
            @RequestBody SignUpRequest request
    ) {
        return ResponseEntity.ok(service.signupUser(request));
    }

    @PostMapping(path = "/login/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> authenticate(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(service.loginUser(request));
    }

    @GetMapping(path = "test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }
}
