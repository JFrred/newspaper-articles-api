package com.example.mapper;

import com.example.dto.ArticleRepresentation;
import com.example.dto.ArticleRequest;
import com.example.model.Article;
import com.example.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ArticleMapperImplTest {
    @Autowired
    private ArticleMapperImpl articleMapper;

    @Test
    void mapToRepresentation() {
        Author author = new Author("firstName", "lastName");
        Article article = new Article("title", "content",
                LocalDate.of(2022, 2, 16),
                "journal name", author, Timestamp.from(Instant.now()));
        ArticleRepresentation actual = articleMapper.mapToRepresentation(article);

        String actualName = actual.getAuthor();
        String expectedName = author.getFirstName() + " " + author.getLastName();

        assertThat(actualName).isEqualTo(expectedName);
    }

    @Test
    void mapRequestToSource() {
        ArticleRequest articleRequest = new ArticleRequest("journal name", "title",
                "content", "firstName", "lastName");

        Article actualArticle = articleMapper.mapRequestToSource(articleRequest);

        assertThat(actualArticle.getAuthor().getFirstName()).isEqualTo(articleRequest.getAuthorFirstName());
        assertThat(actualArticle.getAuthor().getFirstName()).isEqualTo(articleRequest.getAuthorFirstName());
        assertThat(actualArticle.getPublicationDate()).isEqualTo(LocalDate.now());
    }

}