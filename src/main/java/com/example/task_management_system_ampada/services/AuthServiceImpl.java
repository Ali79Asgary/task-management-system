package com.example.task_management_system_ampada.services;

import com.example.task_management_system_ampada.models.User;
import com.example.task_management_system_ampada.repositories.UserRepository;
import com.example.task_management_system_ampada.requests.LoginRequest;
import com.example.task_management_system_ampada.requests.SignUpRequest;
import com.example.task_management_system_ampada.responses.LoginResponse;
import com.example.task_management_system_ampada.responses.SignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        User user = repository.findUserByUsername(request.getUsername());
        String token = jwtService.generateToken(user);
        return LoginResponse.builder().token(token).build();
    }

    @Override
    public SignUpResponse signupUser(SignUpRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        repository.save(user);
        String token = jwtService.generateToken(user);
        return SignUpResponse.builder().token(token).build();
    }
}
