package com.example.task_management_system_ampada.services;

import com.example.task_management_system_ampada.exceptions.UserNotFoundException;
import com.example.task_management_system_ampada.models.User;
import com.example.task_management_system_ampada.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
