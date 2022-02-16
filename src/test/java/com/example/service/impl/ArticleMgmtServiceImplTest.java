package com.example.service.impl;

import com.example.dto.ArticleRequest;
import com.example.mapper.ArticleMapper;
import com.example.model.Article;
import com.example.model.Author;
import com.example.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArticleMgmtServiceImplTest {

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private ArticleMapper articleMapper;

    private ArticleMgmtServiceImpl articleService;

    private Article article;
    private ArticleRequest articleRequest;

    @BeforeEach
    void setup() {
        articleService = new ArticleMgmtServiceImpl(articleRepository, articleMapper);

        article = new Article("title", "content", LocalDate.now(),
                "journalName", new Author("firstname", "lastname"), Timestamp.from(Instant.now()));
        articleRequest = new ArticleRequest("Washington Post", "title", "content", "firstName", "lastName");
    }

    @Test
    void create() {
        given(articleRepository.save(any())).willReturn(article);
        given(articleMapper.mapRequestToSource(any())).willReturn(article);

        articleService.create(articleRequest);

        verify(articleRepository).save(article);
    }

    @Test
    void update() {
        given(articleRepository.save(any())).willReturn(article);
        given(articleRepository.findById(anyInt())).willReturn(Optional.of(article));

        articleService.update(1, articleRequest);

        verify(articleRepository).save(article);
    }

    @Test
    void delete() {
        given(articleRepository.findById(anyInt())).willReturn(Optional.of(article));
        articleService.delete(1);

        verify(articleRepository).delete(article);
    }
}