package integration.test.controller;

import com.app.bingoonline.BingoOnlineApplication;
import com.app.bingoonline.controller.ContestController;
import com.app.bingoonline.service.ContestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = BingoOnlineApplication.class)
@AutoConfigureMockMvc
public class ContestControllerIntegrationTest {
    @Mock
    private ContestService contestService;
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void initConfig(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Deve retornar um contest quando chamar a integracao")
    public void createContestTest() throws Exception {
        Map<String, Set<Integer>> contest = new HashMap<>();
        Set<Integer> contestNumber = new HashSet<>();
        contestNumber.add(1001);
        contest.put("contest", contestNumber);

        when(contestService.createContest()).thenReturn(contest);

        this.mockMvc.perform(post("/v1/contests")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contest").isArray())
                .andExpect(jsonPath("$.contest").isNotEmpty())
                .andExpect(jsonPath("$.contest[0]").isNumber())
                .andExpect(jsonPath("$.contest[0]")
                        .value(greaterThanOrEqualTo(1000)))
                .andExpect(jsonPath("$.contest[0]")
                        .value(lessThanOrEqualTo(9999)));

    }
}
