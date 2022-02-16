package com.example.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest {
    private String journalName;
    private String title;
    private String content;
    private String authorFirstName;
    private String authorLastName;
}
