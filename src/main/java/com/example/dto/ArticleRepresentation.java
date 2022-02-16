package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ArticleRepresentation {
    private LocalDate publicationDate;
    private String journalName;
    private String author;
    private String content;
}
