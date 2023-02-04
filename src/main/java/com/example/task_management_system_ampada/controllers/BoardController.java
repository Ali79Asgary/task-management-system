package com.example.task_management_system_ampada.controllers;

import com.example.task_management_system_ampada.models.Board;
import com.example.task_management_system_ampada.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/boards")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(path = "/{id}")
    public Optional<Board> findBoardById(@PathVariable String id) {
        return boardService.findBoardById(id);
    }

    @GetMapping(path = "/")
    public List<Board> findAllBoards() {
        return boardService.findAllBoards();
    }

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Board saveBoard(@RequestBody Board board) {
        return boardService.saveBoard(board);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Board updateBoard(@PathVariable String id, @RequestBody Board board) {
        return boardService.updateBoard(id, board);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBoardById(@PathVariable String id) {
        boardService.deleteBoardById(id);
    }
}

