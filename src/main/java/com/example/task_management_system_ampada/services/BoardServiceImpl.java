package com.example.task_management_system_ampada.services;

import com.example.task_management_system_ampada.exceptions.BoardNotFoundException;
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
        if (boardRepository.findById(id).isPresent())
            return boardRepository.findById(id);
        else
            throw new BoardNotFoundException();
    }

    @Override
    public List<Board> findAllBoards() {
        List<Board> boards = boardRepository.findAll();
        if (!boards.isEmpty())
            return boards;
        else
            throw new BoardNotFoundException();
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
            throw new BoardNotFoundException();
        });
    }

    @Override
    public void deleteBoardById(String id) {
        if (boardRepository.existsById(id))
            boardRepository.deleteById(id);
        else
            throw new BoardNotFoundException();
    }
}
