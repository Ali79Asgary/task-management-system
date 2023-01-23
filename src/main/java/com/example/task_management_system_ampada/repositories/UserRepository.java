package com.example.task_management_system_ampada.repositories;

import com.example.task_management_system_ampada.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUsername(String username);
}
