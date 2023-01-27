package com.example.task_management_system_ampada.config;

import com.example.task_management_system_ampada.models.Board;
import com.example.task_management_system_ampada.models.User;
import com.example.task_management_system_ampada.repositories.BoardRepository;
import com.example.task_management_system_ampada.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDateTime;
import java.util.ArrayList;

@EnableMongoRepositories(basePackageClasses = BoardRepository.class)
@Configuration
public class MongoConfig {
    @Bean
    CommandLineRunner commandLineRunner(BoardRepository boardRepository) {
        return strings -> {
//            userRepository.save(new User("Ali", "Ali"));
//            userRepository.save(new User("Ali", "Ali"));
//            boardRepository.save(
//                    new Board(
//                            "First",
//                            LocalDateTime.now(),
//                            LocalDateTime.now(),
//                            "",
//                            new ArrayList<String>()));
        };
    }

}