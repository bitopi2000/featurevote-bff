package com.featurevote.bff.resource;

import com.featurevote.bff.domain.Board;
import com.featurevote.bff.domain.User;
import com.featurevote.bff.repository.BoardRepository;
import com.featurevote.bff.service.LLMService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LLMController.class)
@AutoConfigureMockMvc(addFilters = false)
class LLMControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private LLMService llmService;
    @MockitoBean
    private BoardRepository boardRepository;

    @Test
    public void shouldReturnSuggestedBoard() throws Exception{
        List<Board> boards = List.of(
                new Board(UUID.randomUUID(), "Admin",
                        "Admin features",
                        new User(UUID.randomUUID(), "testuser1", "testpwd", "t@y.com", User.Role.USER, LocalDateTime.now()),
                        LocalDateTime.now(),
                        LocalDateTime.now()),
                new Board(UUID.randomUUID(),
                        "User",
                        "User-facing",
                        new User(UUID.randomUUID(), "testuser2", "testpwd2", "y@t.com", User.Role.USER, LocalDateTime.now()),
                        LocalDateTime.now(),
                        LocalDateTime.now())
        );
        List<String> boardNames = boards.stream().map(
                board -> board.getName() + ": " + board.getDescription()
        ).toList();

        when(boardRepository.findAll()).thenReturn(boards);
        when(llmService.suggestBoard("Add login", boardNames)).thenReturn("User");

        mockMvc.perform(post("/api/ai/suggest-board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"Add login\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("User"));
    }

}