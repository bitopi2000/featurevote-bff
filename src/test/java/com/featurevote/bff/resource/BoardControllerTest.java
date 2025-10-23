package com.featurevote.bff.resource;

import com.featurevote.bff.domain.Board;
import com.featurevote.bff.domain.User;
import com.featurevote.bff.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
@AutoConfigureMockMvc(addFilters = false)
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private BoardService boardService;

    @Test
    void shouldReturnListOfBoards() throws Exception {
        List<Board> boards = List.of(
                new Board(UUID.randomUUID(), "Mobile",
                        "Mobile features",
                        new User(UUID.randomUUID(), "testuser1", "testpwd", "t@y.com", User.Role.USER, LocalDateTime.now()),
                        LocalDateTime.now(),
                        LocalDateTime.now()),
                new Board(UUID.randomUUID(),
                        "Web",
                        "Web features",
                        new User(UUID.randomUUID(), "testuser2", "testpwd2", "y@t.com", User.Role.USER, LocalDateTime.now()),
                        LocalDateTime.now(),
                        LocalDateTime.now())
        );

        when(boardService.getAllBoards()).thenReturn(boards);

        mockMvc.perform(get("/api/boards/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Mobile"));
    }

}