package com.example.task_management_system_ampada.services;

import com.example.task_management_system_ampada.models.Card;

import java.util.List;
import java.util.Optional;

public interface CardService {

    Optional<Card> findCardById(String id);

    List<Card> findAllCards();

    Card saveCard(Card card);

    Card updateCard(String id, Card newCard);

    void deleteCardById(String id);

}
