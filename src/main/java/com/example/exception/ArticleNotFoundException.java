package com.example.exception;

public class ArticleNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Could not find article with id=";

    public ArticleNotFoundException(int id) {
        super(MESSAGE + id);
    }
}
