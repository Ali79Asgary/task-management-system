package com.example.task_management_system_ampada.repositories;

import com.example.task_management_system_ampada.models.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardRepository extends MongoRepository<Card, String> {
    Card findCardByCardTitle(String cardTitle);
}
