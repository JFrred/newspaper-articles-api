package com.example.service.impl;

import com.example.dto.ArticleRepresentation;
import com.example.dto.ArticlesResponse;
import com.example.exception.ArticleNotFoundException;
import com.example.mapper.ArticleMapper;
import com.example.model.Article;
import com.example.model.Author;
import com.example.repository.ArticleRepository;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ArticleServiceImplTest {
    @MockBean
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleMapper articleMapper;

    private ArticleServiceImpl articleService;
    @Mock
    private Author authorA;

    @BeforeEach
    void setup() {
        articleService = new ArticleServiceImpl(articleRepository, articleMapper);

    }

    @Test
    void getAllSortedByDate() {
        given(articleRepository.findByOrderByPublicationDateDesc()).willReturn(mockedData());
        ArticlesResponse sortedArticles = articleService.getAllSortedByDate();

        ArticleRepresentation newestArticle = sortedArticles.getArticles().get(0);
        ArticleRepresentation oldestArticle = sortedArticles.getArticles().get(sortedArticles.getArticles().size() - 1);

        assertThat(newestArticle.getPublicationDate()).isAfter(oldestArticle.getPublicationDate());
    }

    @Test
    void getById() {
        Article article = new Article("title", "content", LocalDate.now(),
                "journalName", authorA, Timestamp.from(Instant.now()));
        given(articleRepository.findById(anyInt())).willReturn(Optional.of(article));

        ArticleRepresentation articleResponse = articleService.getById(1);
        assertThat(articleResponse.getJournalName()).isEqualTo(articleResponse.getJournalName());
    }

    @Test
    void getById_shouldFail() {
        given(articleRepository.findById(anyInt())).willThrow(ArticleNotFoundException.class);

        assertThrows(ArticleNotFoundException.class, () -> articleService.getById(1));
    }

    @Test
    void getAllByKeyword() {
        given(articleRepository.findByContainingKeyword(anyString())).willReturn(mockedData());

        ArticlesResponse articles = articleService.getAllByKeyword("keyword");

        assertThat(articles.getArticles()).hasSize(mockedData().size());
    }

    private List<Article> mockedData() {
        LocalDate currentDate = LocalDate.now();
        Timestamp createdAt = Timestamp.valueOf(LocalDateTime.of(2000, 1, 1, 1, 1, 1));

        return List.of(
                new Article("article1", "content1", currentDate.minusYears(3), "New York Times", authorA, createdAt),
                new Article("article2", "content2", currentDate.minusYears(10), "Washington Post", authorA, createdAt),
                new Article("article3", "content3", currentDate.minusYears(2), "New York Post", authorA, createdAt),
                new Article("article5", "content5", currentDate.minusYears(15), "Washington Post", authorA, createdAt),
                new Article("article4", "content4", currentDate.minusYears(6), "New York Times", authorA, createdAt)
        );
    }
}