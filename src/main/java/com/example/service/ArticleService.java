package com.example.service;

import com.example.dto.ArticleRepresentation;
import com.example.dto.ArticlesResponse;

public interface ArticleService {
    ArticlesResponse getAllSortedByDate();
    ArticleRepresentation getById(int id);
    ArticlesResponse getAllByKeyword(String keyWord);
}
