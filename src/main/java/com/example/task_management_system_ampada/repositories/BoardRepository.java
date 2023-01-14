package com.example.task_management_system_ampada.repositories;

import com.example.task_management_system_ampada.models.Board;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BoardRepository extends MongoRepository<Board, String> {
}
