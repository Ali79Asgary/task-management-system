package com.example.task_management_system_ampada.services;

import com.example.task_management_system_ampada.models.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {

    Optional<Board> findBoardById(String id);

    List<Board> findAllBoards();

    Board saveBoard(Board board);

    Board updateBoard(String id, Board newBoard);

    void deleteBoardById(String id);

}
