package com.example.mapper;

import com.example.dto.ArticleRequest;
import com.example.dto.ArticleRepresentation;
import com.example.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleRepresentation mapToRepresentation(Article article);

    @Mapping(target = "publicationDate", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "createdAt", expression = "java(java.sql.Timestamp.from(java.time.Instant.now()))")
    Article mapRequestToSource(ArticleRequest articleRequest);
}
