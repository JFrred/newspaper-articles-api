package com.example.controller;

import com.example.controller.handler.GlobalExceptionHandler;
import com.example.dto.ArticleRepresentation;
import com.example.dto.ArticlesResponse;
import com.example.exception.ArticleNotFoundException;
import com.example.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ArticleControllerTest {
    private final String PATH = "/articles";
    private final String PATH_WITH_ID = "/articles/{id}";

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;

    @MockBean
    private ArticleService articleService;

    private ArticleRepresentation articleRepresentation1;
    private ArticleRepresentation articleRepresentation2;
    private ArticleRepresentation articleRepresentation3;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ArticleController(articleService))
                .setControllerAdvice(globalExceptionHandler).build();

        articleRepresentation1 = new ArticleRepresentation(LocalDate.now(), "New York Times", "Author A", "title1", "some test content");
        articleRepresentation2 = new ArticleRepresentation(LocalDate.now().minusDays(1), "Newsday", "Author A", "title2", "some test content xyz");
        articleRepresentation3 = new ArticleRepresentation(LocalDate.now().minusDays(2), "Washington Post", "Author B", "title3", "some test content abc");

        ArticlesResponse articlesResponse = new ArticlesResponse(List.of(articleRepresentation1, articleRepresentation2, articleRepresentation3));

        given(articleService.getAllSortedByDate()).willReturn(articlesResponse);
        given(articleService.getById(1)).willReturn(articleRepresentation1);
        given(articleService.getAllByKeyword(anyString())).willReturn(articlesResponse);

    }

    @Test
    void getAll_shouldSucceed() throws Exception {
        String responseJson = mockMvc.perform(get(PATH))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ArticlesResponse actualResponse = objectMapper.readValue(responseJson, ArticlesResponse.class);

        assertThat(actualResponse.getArticles()).hasSize(3);
    }

    @Test
    void getById_shouldSucceed() throws Exception {
        String responseJson = mockMvc.perform(get(PATH_WITH_ID, 1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ArticleRepresentation actualResponse = objectMapper.readValue(responseJson, ArticleRepresentation.class);

        assertThat(actualResponse.getJournalName()).isEqualTo(articleRepresentation1.getJournalName());
    }

    @Test
    void getById_shouldFail_status404() throws Exception {
        given(articleService.getById(anyInt())).willThrow(ArticleNotFoundException.class);

        int invalidId = 999;

        mockMvc.perform(get(PATH_WITH_ID, invalidId))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllByKeyWord_shouldSucceed() throws Exception {
        String keyword = "test keyword";
        String responseJson = mockMvc.perform(get(PATH + "/name")
                        .param("keyword", keyword))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ArticlesResponse actualResponse = objectMapper.readValue(responseJson, ArticlesResponse.class);

        assertThat(actualResponse.getArticles()).hasSize(3);
    }

}