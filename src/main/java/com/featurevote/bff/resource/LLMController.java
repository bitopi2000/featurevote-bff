package com.featurevote.bff.resource;

import com.featurevote.bff.repository.BoardRepository;
import com.featurevote.bff.service.LLMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/ai")
public class LLMController {

    private final LLMService llmService;
    private final BoardRepository boardRepository;

    @Autowired
    public LLMController (LLMService llmService, BoardRepository boardRepository){
        this.llmService = llmService;
        this.boardRepository = boardRepository;
    }

    @PostMapping("/suggest-board")
    public String suggestBoard(@RequestBody Map<String, String> body) {
        String description = body.get("description");


        List<String> boards = this.boardRepository.findAll()
                .stream().map(
                        board -> board.getName() + ": " + board.getDescription()
                ).toList();
        return llmService.suggestBoard(description, boards);
    }
}