package com.example.task_management_system_ampada.services;

import com.example.task_management_system_ampada.exceptions.UserAlreadyExistsException;
import com.example.task_management_system_ampada.exceptions.UserNotFoundException;
import com.example.task_management_system_ampada.exceptions.UsernameOrPasswordIsWrongException;
import com.example.task_management_system_ampada.models.User;
import com.example.task_management_system_ampada.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenServiceImpl tokenService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenServiceImpl tokenService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public Optional<User> findUserById(String id) {
        if (userRepository.findById(id).isPresent())
            return userRepository.findById(id);
        else
            throw new UserNotFoundException();
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user != null)
            return user;
        else
            throw new UserNotFoundException();
    }

    @Override
    public String loginUser(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if (user != null) {
            if (user.getPassword().equals(bCryptPasswordEncoder.encode(password))) {
                String token = tokenService.generateToken(new Authentication(username, password))
                return
            } else {
                throw new UsernameOrPasswordIsWrongException();
            }
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User signupUser(String username, String password) {
        if (!userRepository.existsUserByUsername(username)) {
            User user = new User(username, password);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException();
        }
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty())
            return users;
        else
            throw new UserNotFoundException();
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String id, User newUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            return userRepository.save(user);
        }).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
    }

    @Override
    public void deleteUserById(String id) {
        if (userRepository.existsById(id))
            userRepository.deleteById(id);
        else
            throw new UserNotFoundException();
    }
}
