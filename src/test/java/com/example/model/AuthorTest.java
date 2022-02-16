package com.example.model;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AuthorTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void emptyConstruct_shouldFail() {
        Set<ConstraintViolation<Author>> validate = validator.validate(new Author());

        assertThat(validate).hasSize(2);
    }

    @Test
    void constructor_withEmptyValues_shouldFail() {
        Author author = new Author("", "");
        Set<ConstraintViolation<Author>> validate = validator.validate(author);

        assertThat(validate).isEmpty();
    }

}