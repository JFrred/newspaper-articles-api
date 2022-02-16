package com.example.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest {
    @NotNull(message = "Journal name must not be null")
    @NotEmpty(message = "Journal name must not be empty")
    private String journalName;
    @NotNull(message = "Title must not be null")
    @NotEmpty(message = "Title must not be empty")
    private String title;
    @NotNull(message = "Content must not be null")
    @NotEmpty(message = "Content must not be empty")
    private String content;
    @NotNull(message = "Author first name must not be null")
    @NotEmpty(message = "Author first name must not be empty")
    private String authorFirstName;
    @NotNull(message = "Author last name must not be null")
    @NotEmpty(message = "Author last name must not be empty")
    private String authorLastName;
}
