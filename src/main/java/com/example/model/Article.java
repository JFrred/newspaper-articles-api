package com.example.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Title must not be null")
    @NotEmpty(message = "Title must not be empty")
    private String title;

    @NotNull(message = "Content must not be null")
    @NotEmpty(message = "Content must not be empty")
    private String content;

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @NotNull(message = "Journal name must not be null")
    @NotEmpty(message = "Journal name must not be empty")
    @Column(name = "publication_name")
    private String journalName;

    @Embedded
    private Author author;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    public Article(String title, String content, LocalDate publicationDate, String journalName, Author author, Timestamp createdAt) {
        this.title = title;
        this.content = content;
        this.publicationDate = publicationDate;
        this.journalName = journalName;
        this.author = author;
        this.createdAt = createdAt;
    }
}
