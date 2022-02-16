package com.example.mapper;

import com.example.dto.ArticleRepresentation;
import com.example.model.Article;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleRepresentation mapToRepresentation(Article article);
}
