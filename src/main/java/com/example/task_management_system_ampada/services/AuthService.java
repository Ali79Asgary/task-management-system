package com.example.task_management_system_ampada.services;

import com.example.task_management_system_ampada.requests.LoginRequest;
import com.example.task_management_system_ampada.requests.SignUpRequest;
import com.example.task_management_system_ampada.responses.LoginResponse;
import com.example.task_management_system_ampada.responses.SignUpResponse;

public interface AuthService {

    LoginResponse loginUser(LoginRequest request);

    SignUpResponse signupUser(SignUpRequest request);
}
