package com.example.mapper;

import com.example.dto.ArticleRepresentation;
import com.example.dto.ArticleRequest;
import com.example.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    @Mapping(target = "author", expression = "java(String.format(\"%s %s\", article.getAuthor().getFirstName(), article.getAuthor().getLastName()))")
    ArticleRepresentation mapToRepresentation(Article article);

    @Mapping(target = "author.firstName", source = "authorFirstName")
    @Mapping(target = "author.lastName", source = "authorLastName")
    @Mapping(target = "publicationDate", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "createdAt", expression = "java(java.sql.Timestamp.from(java.time.Instant.now()))")
    Article mapRequestToSource(ArticleRequest articleRequest);

}
