package com.example.service.impl;

import com.example.dto.ArticleRequest;
import com.example.exception.ArticleNotFoundException;
import com.example.model.Article;
import com.example.repository.ArticleRepository;
import com.example.service.ArticleMgmtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ArticleMgmtServiceImpl implements ArticleMgmtService {
    private final ArticleRepository articleRepository;

    @Override
    public int create(ArticleRequest articleRequest) {
        Article article = new Article(articleRequest.getTitle(), articleRequest.getContent(), LocalDate.now(),
                articleRequest.getJournalName(), articleRequest.getAuthor(), Timestamp.from(Instant.now()));
        return articleRepository.save(article).getId();
    }

    @Override
    public int update(int id, ArticleRequest createRequest) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));

        article.setJournalName(createRequest.getJournalName());
        article.setAuthor(createRequest.getAuthor());
        article.setContent(createRequest.getContent());
        article.setTitle(createRequest.getTitle());
        article.setPublicationDate(LocalDate.now());

        return articleRepository.save(article).getId();
    }

    @Override
    public void delete(int id) {
        articleRepository.deleteById(id);
    }
}
