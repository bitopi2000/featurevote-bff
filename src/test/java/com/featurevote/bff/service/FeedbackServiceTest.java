package com.featurevote.bff.service;

import com.featurevote.bff.controller.dto.FeedbackDto;
import com.featurevote.bff.domain.Board;
import com.featurevote.bff.domain.Feedback;
import com.featurevote.bff.domain.User;
import com.featurevote.bff.repository.BoardRepository;
import com.featurevote.bff.repository.FeedbackRepository;
import com.featurevote.bff.repository.UserRepository;
import com.featurevote.bff.repository.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FeedbackServiceTest {

    private final FeedbackRepository feedbackRepository = mock(FeedbackRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final BoardRepository boardRepository = mock(BoardRepository.class);
    private final VoteRepository voteRepository = mock(VoteRepository.class);

    private FeedbackService feedbackService;

    @BeforeEach
    void setUp() {
        feedbackService = new FeedbackService(feedbackRepository,
                userRepository,
                boardRepository, voteRepository);
    }

    @Test
    void getFeedbacksForBoardId() {
        Board board = mock(Board.class);
        Feedback feedback = new Feedback();
        feedback.setTitle("title");
        feedback.setDescription("description");
        feedback.setOwner(mock(User.class));
        feedback.setBoard(board);

        when(feedbackRepository.findByBoardId(board.getId())).thenReturn(List.of(feedback));

        var result = feedbackService.getFeedbacksForBoardId(board.getId());
        assertNotNull(result);
        assertEquals("title", result.get(0).getTitle());
        assertEquals("description", result.get(0).getDescription());
    }

    @Test
    void getEmptyFeedbacks() {
        var board = mock(Board.class);
        when(feedbackRepository.findByBoardId(board.getId())).thenReturn(List.of());

        var result = feedbackService.getFeedbacksForBoardId(board.getId());
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void getFeedbacksFail() {
        var board = mock(Board.class);
        when(feedbackRepository.findByBoardId(board.getId())).thenThrow(new RuntimeException("No new feedback found"));

        var exception = assertThrows(RuntimeException.class, () -> feedbackService.getFeedbacksForBoardId(board.getId()));
        assertEquals("No new feedback found", exception.getMessage());
    }

    @Test
    void saveNewFeedback() {
        FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setTitle("title");
        feedbackDto.setDescription("description");
        feedbackDto.setOwnerName("bitopi@example.com");
        feedbackDto.setStatus("SUBMITTED");
        Board board = new Board();
        board.setName("bitopi");
        board.setDescription("bitopi");
        User user = mock(User.class);
        board.setOwner(user);
        board.setId(UUID.randomUUID());

        when(userRepository.findByEmail(any(String.class))).thenReturn(user);
        when(boardRepository.findById(any(UUID.class))).thenReturn(board);

        feedbackService.saveNewFeedback(board.getId(), feedbackDto);
    }

    @Test
    void saveNewVote() {
        feedbackService.saveNewVote(UUID.randomUUID(), "test@example.com");
    }
}