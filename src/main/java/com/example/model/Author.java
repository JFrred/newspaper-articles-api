package com.example.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Author {
    @NotNull
    @Column(name = "author_first_name")
    private String firstName;

    @NotNull
    @Column(name = "author_last_name")
    private String lastName;
}
