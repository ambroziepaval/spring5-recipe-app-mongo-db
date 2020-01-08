package com.ambroziepaval.spring5recipeapp.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
    }

    @Test
    void getId() {
        String idValue = "4";

        category.setId(idValue);

        assertEquals(idValue, category.getId());
    }

    @Test
    void getDescription() {
        String descriptionValue = "Test description";

        category.setDescription(descriptionValue);

        assertEquals(descriptionValue, category.getDescription());
    }
}
