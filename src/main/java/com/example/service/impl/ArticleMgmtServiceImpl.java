package com.example.service.impl;

import com.example.dto.ArticleRequest;
import com.example.exception.ArticleNotFoundException;
import com.example.mapper.ArticleMapper;
import com.example.model.Article;
import com.example.model.Author;
import com.example.repository.ArticleRepository;
import com.example.service.ArticleMgmtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ArticleMgmtServiceImpl implements ArticleMgmtService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public int create(ArticleRequest articleRequest) {

        return articleRepository.save(articleMapper.mapRequestToSource(articleRequest)).getId();
    }

    @Override
    public int update(int id, ArticleRequest updateRequest) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));

        article.setJournalName(updateRequest.getJournalName());
        article.setAuthor(new Author(updateRequest.getAuthorFirstName(), updateRequest.getAuthorLastName()));
        article.setContent(updateRequest.getContent());
        article.setTitle(updateRequest.getTitle());
        article.setPublicationDate(LocalDate.now());

        return articleRepository.save(article).getId();
    }

    @Override
    public void delete(int id) {
        articleRepository.deleteById(id);
    }
}
