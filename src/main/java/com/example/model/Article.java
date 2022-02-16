package com.example.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title_with_contents")
    private String content;
    @Column(name = "publication_date")
    private LocalDate publicationDate;
    @Column(name = "publication_name")
    private String journalName;
    private String author;
    @Column(name = "created_at")
    private Timestamp createdAt;
}
