package com.example.task_management_system_ampada.services;

import com.example.task_management_system_ampada.exceptions.BoardNotFoundException;
import com.example.task_management_system_ampada.exceptions.UserNotFoundException;
import com.example.task_management_system_ampada.models.Board;
import com.example.task_management_system_ampada.repositories.BoardRepository;
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

public class BoardServiceImplTest {

    @Mock
    private BoardRepository repository;

    @Autowired
    private BoardServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new BoardServiceImpl(repository);
    }

    @Test
    void testFindBoardById_BoardFounded_returnBoard() {
        Optional<Board> board = Optional.of(
                new Board(
                        "user",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "creatorId",
                        new ArrayList<>(List.of("cardId")
                        )
                )
        );
        given(repository.findById(board.get().getId())).willReturn(board);
        Optional<Board> exceptedBoard = service.findBoardById(board.get().getId());
        Assertions.assertEquals(board, exceptedBoard);
    }

    @Test
    void testFindBoardById_BoardNotFound_throwBoardNotFoundException() {
        Board board = new Board(
                "user",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "creatorId",
                new ArrayList<>(List.of("cardId")
                )
        );
        given(repository.findById(board.getId())).willReturn(Optional.empty());
        Assertions.assertThrows(BoardNotFoundException.class, () -> {
            service.findBoardById(board.getId());
        });
    }

    @Test
    void testFindAllBoards_BoardsFounded_returnBoards() {
        Board board1 = new Board(
                "user",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "creatorId",
                new ArrayList<>(List.of("cardId")
                )
        );
        Board board2 = new Board(
                "user",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "creatorId",
                new ArrayList<>(List.of("cardId")
                )
        );
        List<Board> boards = List.of(board1, board2);
        given(repository.findAll()).willReturn(boards);
        List<Board> exceptedBoards = service.findAllBoards();
        Assertions.assertEquals(boards, exceptedBoards);
    }

    @Test
    void testFindAllBoards_BoardsNotFound_throwBoardNotFoundException() {
        given(repository.findAll()).willReturn(List.of());
        Assertions.assertThrows(BoardNotFoundException.class, () -> {
            service.findAllBoards();
        });
    }

    @Test
    void testSaveBoard_BoardSaved_returnBoard() {
        Board board = new Board(
                "user",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "creatorId",
                new ArrayList<>(List.of("cardId")
                )
        );
        given(repository.save(board)).willReturn(board);
        Board exceptedBoard = service.saveBoard(board);
        Assertions.assertEquals(exceptedBoard, board);
    }

    @Test
    void testDeleteBoardById_BoardDeleted_return() {
        Board board = new Board(
                "user",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "creatorId",
                new ArrayList<>(List.of("cardId")
                )
        );
        repository.save(board);
        given(repository.existsById(board.getId())).willReturn(true);
        willDoNothing().given(repository).deleteById(board.getId());
        service.deleteBoardById(board.getId());
        verify(repository).deleteById(board.getId());
    }

    @Test
    void testUpdateBoard_BoardUpdated_returnBoard() {
        Optional<Board> board = Optional.of(
                new Board(
                        "user",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "creatorId",
                        new ArrayList<>(List.of("cardId")
                        )
                )
        );
        given(repository.findById(board.get().getId())).willReturn(board);
        given(repository.save(board.get())).willReturn(board.get());
        Board exceptedBoard = service.updateBoard(board.get().getId(), board.get());
        Assertions.assertNotNull(exceptedBoard);
        verify(repository).save(any(Board.class));
    }

    @Test
    void testUpdateBoard_BoardNotFound_throwBoardNotFoundException() {
        Optional<Board> board = Optional.of(
                new Board(
                        "user",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "creatorId",
                        new ArrayList<>(List.of("cardId")
                        )
                )
        );
        given(repository.findById(board.get().getId())).willReturn(Optional.empty());
        Assertions.assertThrows(BoardNotFoundException.class, () -> {
            service.updateBoard(board.get().getId(), board.get());
        });
    }
}
