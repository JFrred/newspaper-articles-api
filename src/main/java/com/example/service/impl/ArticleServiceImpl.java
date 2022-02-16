package com.example.service.impl;

import com.example.dto.ArticleRepresentation;
import com.example.dto.ArticlesResponse;
import com.example.exception.ArticleNotFoundException;
import com.example.mapper.ArticleMapper;
import com.example.repository.ArticleRepository;
import com.example.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public ArticlesResponse getAllSortedByDate() {
        return new ArticlesResponse(
                articleRepository.findByOrderByPublicationDateDesc()
                .stream().map(articleMapper::mapToRepresentation)
                .collect(Collectors.toList()));
    }

    @Override
    public ArticleRepresentation getById(int id) {
        return articleRepository.findById(id).map(articleMapper::mapToRepresentation)
                .orElseThrow(() -> new ArticleNotFoundException("Could not find article with id=", id));
    }

    @Override
    public ArticlesResponse getAllByKeyword(String keyword) {
        return new ArticlesResponse(
                articleRepository.findByContainingKeyword(keyword)
                .stream().map(articleMapper::mapToRepresentation)
                .collect(Collectors.toList()));
    }
}
