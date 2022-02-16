package com.example.mapper;

import com.example.dto.ArticleRepresentation;
import com.example.dto.ArticleRequest;
import com.example.model.Article;
import com.example.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleMapperImplTest {
    @Autowired
    private ArticleMapperImpl articleMapper;

    private Author author;
    private Article article;

    @BeforeEach
    void setup() {
        author = new Author("firstName", "lastName");
        article = new Article("title", "content",
                LocalDate.of(2022, 2, 16),
                "journal name", author, Timestamp.from(Instant.now()));
    }

    @Test
    void mapToRepresentation() {
        Author author = new Author("firstName", "lastName");
        Article article = new Article("title", "content",
                LocalDate.of(2022, 2, 16),
                "journal name", author, Timestamp.from(Instant.now()));
        ArticleRepresentation actual = articleMapper.mapToRepresentation(article);

        assertThat(actual.getAuthor()).isEqualTo(author.getFirstName() + " " + author.getLastName());
    }

    @Test
    void mapRequestToSource() {
        ArticleRequest articleRequest = new ArticleRequest("journal name", "title",
                "content", "firstName", "lastName");

        Article article = articleMapper.mapRequestToSource(articleRequest);

        assertThat(article.getAuthor().getFirstName()).isEqualTo(articleRequest.getAuthorFirstName());
        assertThat(article.getAuthor().getFirstName()).isEqualTo(articleRequest.getAuthorFirstName());
        assertThat(article.getPublicationDate()).isEqualTo(LocalDate.now());
    }

}