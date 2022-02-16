package com.example.mapper;

import com.example.dto.ArticleCreateRequest;
import com.example.dto.ArticleRepresentation;
import com.example.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleRepresentation mapToRepresentation(Article article);

//    @Mapping()
    Article mapRequestToSource(ArticleCreateRequest articleCreateRequest);
}
