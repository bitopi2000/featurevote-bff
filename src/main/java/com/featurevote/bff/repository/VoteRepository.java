package com.featurevote.bff.repository;

import com.featurevote.bff.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    // @Query("select vo from vote vo join vo.feedback fb where fb.id = ':feedbackId'")
    List<Vote> findByFeedbackId(UUID feedbackId);
}
