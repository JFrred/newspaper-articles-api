package com.example.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
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
