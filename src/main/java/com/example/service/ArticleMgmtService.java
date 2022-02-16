package com.example.service;

import com.example.dto.ArticleRequest;

public interface ArticleMgmtService {
    int create(ArticleRequest articleRequest);
    int update(int id, ArticleRequest articleRequest);
    void delete(int id);
}
