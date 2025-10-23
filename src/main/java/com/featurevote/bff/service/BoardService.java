package com.featurevote.bff.service;

import com.featurevote.bff.domain.Board;
import com.featurevote.bff.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }
    public List<Board> getAllBoards() {
        return this.boardRepository.findAll();
    }

    public Board getBoard(UUID boardId) {
        return this.boardRepository.findById(boardId);
    }
}
