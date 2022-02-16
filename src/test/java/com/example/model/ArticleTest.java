package com.example.model;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void emptyConstruct_shouldFail() {
        Set<ConstraintViolation<Article>> validate = validator.validate(new Article());

        assertThat(validate).hasSize(6);
    }

    @Test
    void constructor_withEmptyValues_shouldFail() {
        Article article = new Article("", "", null, "", null, null);
        Set<ConstraintViolation<Article>> validate = validator.validate(article);

        assertThat(validate).hasSize(3);
    }

}