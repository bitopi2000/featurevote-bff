package com.featurevote.bff.service;

import com.featurevote.bff.controller.dto.FeedbackDto;
import com.featurevote.bff.domain.Board;
import com.featurevote.bff.domain.Feedback;
import com.featurevote.bff.domain.User;
import com.featurevote.bff.domain.Vote;
import com.featurevote.bff.repository.BoardRepository;
import com.featurevote.bff.repository.FeedbackRepository;
import com.featurevote.bff.repository.UserRepository;
import com.featurevote.bff.repository.VoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final VoteRepository voteRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository,
                           UserRepository userRepository,
                           BoardRepository boardRepository,
                           VoteRepository voteRepository) {
        this.feedbackRepository = feedbackRepository;
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
        this.voteRepository = voteRepository;
    }

    public List<Feedback> getFeedbacksForBoardId(UUID boardId){
        return feedbackRepository.findByBoardId(boardId);
    }

    @Transactional
    public void saveNewFeedback(UUID boardId, FeedbackDto feedbackDto) {
        User user = userRepository.findByEmail(feedbackDto.getOwnerName());
        Board board = boardRepository.findById(boardId);
        Feedback feedback = new Feedback();
        feedback.setTitle(feedbackDto.getTitle());
        feedback.setDescription(feedbackDto.getDescription());
        feedback.setStatus(Feedback.Status.valueOf(feedbackDto.getStatus()));
        feedback.setCreatedAt(LocalDateTime.now());
        feedback.setUpdatedAt(LocalDateTime.now());
        feedback.setOwner(user);
        feedback.setBoard(board);
        feedbackRepository.saveAndFlush(feedback);
    }

    @Transactional
    public void saveNewVote(UUID feedbackId, String userName) {
        Feedback feedback = feedbackRepository.findById(feedbackId);
        User user = userRepository.findByEmail(userName);
        Vote vote = new Vote();
        vote.setFeedback(feedback);
        vote.setOwner(user);
        vote.setVoteType("By web");
        vote.setCreatedAt(LocalDateTime.now());
        voteRepository.saveAndFlush(vote);
    }
}
