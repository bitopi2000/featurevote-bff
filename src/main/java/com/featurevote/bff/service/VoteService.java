package com.featurevote.bff.service;

import com.featurevote.bff.domain.Vote;
import com.featurevote.bff.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository){
        this.voteRepository = voteRepository;
    }


    public Integer getVotes(UUID feedbackId) {
        List<Vote> votes = this.voteRepository.findByFeedbackId(feedbackId);
        return votes.size();
    }
}
