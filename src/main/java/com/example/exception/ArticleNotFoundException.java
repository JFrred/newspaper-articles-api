package com.example.exception;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(String message, int id) {
        super(message + id);
    }
}
