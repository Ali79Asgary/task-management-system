package com.example.task_management_system_ampada.services;

import com.example.task_management_system_ampada.exceptions.CardNotFoundException;
import com.example.task_management_system_ampada.models.Card;
import com.example.task_management_system_ampada.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Optional<Card> findCardById(String id) {
        if (cardRepository.findById(id).isPresent())
            return cardRepository.findById(id);
        else
            throw new CardNotFoundException();
    }

    @Override
    public List<Card> findAllCards() {
        List<Card> cards = cardRepository.findAll();
        if (!cards.isEmpty())
            return cards;
        else
            throw new CardNotFoundException();
    }

    @Override
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card updateCard(String id, Card newCard) {
        return cardRepository.findById(id).map(card -> {
            card.setCardTitle(newCard.getCardTitle());
            card.setBoardId(newCard.getBoardId());
            card.setUsersId(newCard.getUsersId());
            card.setModifiedOn(LocalDateTime.now());
            return cardRepository.save(card);
        }).orElseThrow(() -> {
            throw new CardNotFoundException();
        });
    }

    @Override
    public void deleteCardById(String id) {
        if (cardRepository.existsById(id))
            cardRepository.deleteById(id);
        else
            throw new CardNotFoundException();
    }
}
