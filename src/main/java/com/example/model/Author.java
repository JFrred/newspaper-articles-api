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
    private String firstName;

    @NotNull
    private String lastName;
}
