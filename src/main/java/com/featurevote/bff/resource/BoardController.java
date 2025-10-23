package com.featurevote.bff.resource;

import com.featurevote.bff.domain.Board;
import com.featurevote.bff.domain.Feedback;
import com.featurevote.bff.resource.dto.FeedbackDto;
import com.featurevote.bff.resource.dto.SingleBoardDto;
import com.featurevote.bff.service.BoardService;
import com.featurevote.bff.service.FeedbackService;
import com.featurevote.bff.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/boards")
@Tag(name = "Boards", description = "Endpoints for managing boards and feedback")
public class BoardController {

    private final BoardService boardService;
    private final FeedbackService feedbackService;
    private final VoteService voteService;

    @Autowired
    public BoardController(BoardService boardService,
                           FeedbackService feedbackService,
                           VoteService voteService) {
        this.boardService = boardService;
        this.feedbackService = feedbackService;
        this.voteService = voteService;
    }

    @GetMapping("/list")
    @Operation(summary = "List all boards", description = "Endpoint to retrieve all boards")
    public ResponseEntity<List<Board>> listFeatures() {
        List<Board> boards = boardService.getAllBoards();
        if (boards.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(boards);
    }

    @GetMapping("/{boardId}")
    @Operation(summary = "View a single board", description = "Endpoint to retrieve a single board by its ID")
    public ResponseEntity<SingleBoardDto> singleBoardView(@PathVariable UUID boardId) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksForBoardId(boardId);
        Board board = boardService.getBoard(boardId);
        List<FeedbackDto> feedbackDtos = new ArrayList<>();
        for (Feedback feedback: feedbacks) {
            int voteCount = voteService.getVotes(feedback.getId());
            FeedbackDto feedbackDto = new FeedbackDto(
                    feedback.getTitle(),
                    feedback.getDescription(),
                    feedback.getStatus().name(),
                    feedback.getOwner().getEmail(),
                    voteCount
            );
            feedbackDtos.add(feedbackDto);
        }

        SingleBoardDto singleBoardDto = new SingleBoardDto(
                board.getName(),
                feedbackDtos
        );

        return ResponseEntity.ok(singleBoardDto);
    }
}
