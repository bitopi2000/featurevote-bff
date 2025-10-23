package com.featurevote.bff.service;

import com.featurevote.bff.domain.Feedback;
import com.featurevote.bff.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<Feedback> getFeedbacksForBoardId(UUID boardId){
        return feedbackRepository.findByBoardId(boardId);
    }
}
