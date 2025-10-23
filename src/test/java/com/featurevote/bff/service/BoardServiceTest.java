package com.featurevote.bff.service;

import com.featurevote.bff.domain.Board;
import com.featurevote.bff.domain.User;
import com.featurevote.bff.repository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BoardServiceTest {

    private final BoardRepository boardRepository = mock(BoardRepository.class);
    private BoardService boardService;

    @BeforeEach
    void setUp() {
        boardService = new BoardService(boardRepository);
    }

    @Test
    public void testGetAllBoards(){
        List<Board> boards = List.of(
                new Board(UUID.randomUUID(), "Mobile",
                        "Mobile features",
                        new User(UUID.randomUUID(), "testuser1", "testpwd", "t@y.com", User.Role.USER, LocalDateTime.now()),
                        LocalDateTime.now(),
                        LocalDateTime.now()),
                new Board(UUID.randomUUID(),
                        "Web",
                        "Web features",
                        new User(UUID.randomUUID(), "testuser2", "testpwd2", "y@t.com", User.Role.USER, LocalDateTime.now()),
                        LocalDateTime.now(),
                        LocalDateTime.now())
        );

        when(boardRepository.findAll()).thenReturn(boards);
        List<Board> boardList = boardService.getAllBoards();

        assertNotNull(boardList);
        assertEquals(2, boardList.size());

    }

}