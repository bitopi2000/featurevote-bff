package com.featurevote.bff.repository;

import com.featurevote.bff.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Board findById(UUID boardId);
}
