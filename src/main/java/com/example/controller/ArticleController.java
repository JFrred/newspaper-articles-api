package com.example.controller;

import com.example.dto.ArticleRepresentation;
import com.example.dto.ArticlesResponse;
import com.example.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<ArticlesResponse> getAll() {
        return ResponseEntity.ok(articleService.getAllSortedByDate());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleRepresentation> getById(@PathVariable int id) {
        return ResponseEntity.ok(articleService.getById(id));
    }

    @GetMapping("/name")
    public ResponseEntity<ArticlesResponse> getAllByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(articleService.getAllByKeyword(keyword));
    }

}
