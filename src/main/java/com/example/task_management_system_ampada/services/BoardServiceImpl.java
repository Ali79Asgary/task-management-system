package com.example.task_management_system_ampada.services;

import com.example.task_management_system_ampada.models.Board;
import com.example.task_management_system_ampada.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    private BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Optional<Board> findBoardById(String id) {
        return boardRepository.findById(id);
    }

    @Override
    public List<Board> findAllBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public Board updateBoard(String id, Board newBoard) {
        return boardRepository.findById(id).map(board -> {
            board.setBoardName(newBoard.getBoardName());
            board.setCardsId(newBoard.getCardsId());
            board.setCreatorId(newBoard.getCreatorId());
            board.setModifiedOn(LocalDateTime.now());
            return boardRepository.save(board);
        }).orElseThrow(() -> {
            throw new RuntimeException();
        });
    }

    @Override
    public void deleteBoardById(String id) {
        boardRepository.deleteById(id);
    }
}
