package com.example.task_management_system_ampada.controllers;

import com.example.task_management_system_ampada.models.Card;
import com.example.task_management_system_ampada.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/boards/{boardId}/cards")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(path = "/{cardId}")
    public Optional<Card> findCardById(@PathVariable("boardId") String boardId, @PathVariable("cardId") String cardId) {
        return cardService.findCardById(cardId);
    }

//    @GetMapping(path = "/")
//    public Card findCardByCardTitle(@PathVariable("boardId") String boardId, @RequestParam String cardTitle) {
//        return cardService.findCardByCardTitle(cardTitle);
//    }

    @GetMapping(path = "/")
    public List<Card> findAllCards(@PathVariable("boardId") String boardId) {
        return cardService.findAllCards();
    }

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Card saveCard(@PathVariable("boardId") String boardId, @RequestBody Card card) {
        return cardService.saveCard(card);
    }

    @PutMapping(path = "/{cardId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Card updateCard(@PathVariable("boardId") String boardId, @PathVariable("cardId") String cardId, @RequestBody Card card) {
        return cardService.updateCard(cardId, card);
    }

    @DeleteMapping(path = "/{cardId}")
    public void deleteCardById(@PathVariable("boardId") String boardId, @PathVariable("cardId") String cardId) {
        cardService.deleteCardById(cardId);
    }
}

