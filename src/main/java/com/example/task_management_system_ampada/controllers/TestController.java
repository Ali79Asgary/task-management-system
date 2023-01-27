package com.example.task_management_system_ampada.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hi")
@RequiredArgsConstructor
public class TestController {

    @GetMapping("")
    public ResponseEntity<String> hi() {
        return ResponseEntity.ok("hi ali!");
    }
}
