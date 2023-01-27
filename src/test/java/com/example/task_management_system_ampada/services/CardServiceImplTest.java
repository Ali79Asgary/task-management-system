package com.example.task_management_system_ampada.services;

import com.example.task_management_system_ampada.exceptions.CardNotFoundException;
import com.example.task_management_system_ampada.models.Card;
import com.example.task_management_system_ampada.repositories.CardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

public class CardServiceImplTest {
    @Mock
    private CardRepository repository;

    @Autowired
    private CardServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new CardServiceImpl(repository);
    }

    @Test
    void testFindCardById_CardFounded_returnCard() {
        Optional<Card> card = Optional.of(
                new Card(
                        "cardTitle",
                        "boardId",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        new ArrayList<>(List.of("userId"))
                )
        );
        given(repository.findById(card.get().getId())).willReturn(card);
        Optional<Card> exceptedCard = service.findCardById(card.get().getId());
        Assertions.assertEquals(card, exceptedCard);
    }

    @Test
    void testFindCardById_CardNotFound_throwCardNotFoundException() {
        Card card = new Card(
                "cardTitle",
                "boardId",
                LocalDateTime.now(),
                LocalDateTime.now(),
                new ArrayList<>(List.of("userId"))
        );
        given(repository.findById(card.getId())).willReturn(Optional.empty());
        Assertions.assertThrows(CardNotFoundException.class, () -> {
            service.findCardById(card.getId());
        });
    }

    @Test
    void testFindAllCards_CardsFounded_returnCards() {
        Card card1 = new Card(
                "cardTitle",
                "boardId",
                LocalDateTime.now(),
                LocalDateTime.now(),
                new ArrayList<>(List.of("userId"))
        );
        Card card2 = new Card(
                "cardTitle",
                "boardId",
                LocalDateTime.now(),
                LocalDateTime.now(),
                new ArrayList<>(List.of("userId"))
        );
        List<Card> cards = List.of(card1, card2);
        given(repository.findAll()).willReturn(cards);
        List<Card> exceptedCards = service.findAllCards();
        Assertions.assertEquals(cards, exceptedCards);
    }

    @Test
    void testFindAllCards_CardsNotFound_throwCardNotFoundException() {
        given(repository.findAll()).willReturn(List.of());
        Assertions.assertThrows(CardNotFoundException.class, () -> {
            service.findAllCards();
        });
    }

    @Test
    void testFindCardByCardTitle_CardFounded_returnCard() {
        Card card1 = new Card(
                "cardTitle",
                "boardId",
                LocalDateTime.now(),
                LocalDateTime.now(),
                new ArrayList<>(List.of("userId"))
        );
        given(repository.findCardByCardTitle(card1.getCardTitle())).willReturn(card1);
        Card exceptedCard = service.findCardByCardTitle(card1.getCardTitle());
        Assertions.assertEquals(card1, exceptedCard);
    }

    @Test
    void testFindCardByCardTitle_CardNotFound_throwCardNotFoundException() {
        Card card = new Card(
                "cardTitle",
                "boardId",
                LocalDateTime.now(),
                LocalDateTime.now(),
                new ArrayList<>(List.of("userId"))
        );
        given(repository.findCardByCardTitle(card.getCardTitle())).willReturn(null);
        Assertions.assertThrows(CardNotFoundException.class, () -> {
            service.findCardByCardTitle(card.getCardTitle());
        });
    }

    @Test
    void testSaveCard_CardSaved_returnCard() {
        Card card = new Card(
                "cardTitle",
                "boardId",
                LocalDateTime.now(),
                LocalDateTime.now(),
                new ArrayList<>(List.of("userId"))
        );
        given(repository.save(card)).willReturn(card);
        Card exceptedCard = service.saveCard(card);
        Assertions.assertEquals(card, exceptedCard);
    }

    @Test
    void testDeleteCardById_CardDeleted_return() {
        Card card = new Card(
                "cardTitle",
                "boardId",
                LocalDateTime.now(),
                LocalDateTime.now(),
                new ArrayList<>(List.of("userId"))
        );
        repository.save(card);
        given(repository.existsById(card.getId())).willReturn(true);
        willDoNothing().given(repository).deleteById(card.getId());
        service.deleteCardById(card.getId());
        verify(repository).deleteById(card.getId());
    }

    @Test
    void testUpdateCard_CardUpdated_returnCard() {
        Optional<Card> card = Optional.of(
                new Card(
                        "cardTitle",
                        "boardId",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        new ArrayList<>(List.of("userId"))
                )
        );
        given(repository.findById(card.get().getId())).willReturn(card);
        given(repository.save(card.get())).willReturn(card.get());
        Card exceptedCard = service.updateCard(card.get().getId(), card.get());
        Assertions.assertNotNull(exceptedCard);
        verify(repository).save(any(Card.class));
    }

    @Test
    void testUpdateCard_CardNotFound_throwCardNotFoundException() {
        Optional<Card> card = Optional.of(
                new Card(
                        "cardTitle",
                        "boardId",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        new ArrayList<>(List.of("userId"))
                )
        );
        given(repository.findById(card.get().getId())).willReturn(Optional.empty());
        Assertions.assertThrows(CardNotFoundException.class, () -> {
            service.updateCard(card.get().getId(), card.get());
        });
    }
}
