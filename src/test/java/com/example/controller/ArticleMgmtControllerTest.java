package com.example.controller;

import com.example.controller.handler.GlobalExceptionHandler;
import com.example.dto.ArticleRequest;
import com.example.service.ArticleMgmtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ArticleMgmtControllerTest {
    private final String PATH = "/articles";
    private final String PATH_WITH_ID = PATH + "/{id}";

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ArticleMgmtService articleMgmtService;

    private ArticleRequest articleRequest;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ArticleMgmtController(articleMgmtService))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        articleRequest = new ArticleRequest("New York Times",
                "Title here", "some content", "firstname", "lastname");
    }

    @Test
    void create_shouldSucceed_status201() throws Exception {
        String validRequest = objectMapper.writeValueAsString(articleRequest);

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequest))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void update_shouldSucceed_withStatus204() throws Exception {
        int articleId = 1;

        mockMvc.perform(put(PATH_WITH_ID, articleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articleRequest)))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_shouldSucceed_withStatus204() throws Exception {
        int articleId = 1;

        mockMvc.perform(delete(PATH_WITH_ID, articleId))
                .andExpect(status().isNoContent());
    }

}