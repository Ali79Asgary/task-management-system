package com.example.task_management_system_ampada.services;

import org.springframework.security.core.Authentication;

public interface TokenService {

    String generateToken(Authentication authentication);
}
