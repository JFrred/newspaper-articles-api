package com.example.controller;

import com.example.dto.ArticleRequest;
import com.example.service.ArticleMgmtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleMgmtController {
    private final ArticleMgmtService articleMgmtService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody ArticleRequest articleRequest) {
        articleMgmtService.create(articleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @Valid @RequestBody ArticleRequest articleRequest) {
        articleMgmtService.update(id, articleRequest);
        return new ResponseEntity<>("Product has been updated", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        articleMgmtService.delete(id);
        return new ResponseEntity<>("Product has been deleted", HttpStatus.NO_CONTENT);
    }
}
