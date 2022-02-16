package com.example.repository;

import com.example.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findByOrderByPublicationDateDesc();
    @Query("Select a from Article a where a.title like %:keyword% or a.content like %:keyword%")
    List<Article> findByContainingKeyword(@Param("keyword") String keyword);
}
