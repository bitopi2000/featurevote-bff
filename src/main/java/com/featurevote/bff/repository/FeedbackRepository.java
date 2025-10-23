package com.featurevote.bff.repository;

import com.featurevote.bff.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    // @Query("select fb from feedback fb join fb.board bd where bd.id = ':boardId'")
    List<Feedback> findByBoardId(UUID boardId);
}
