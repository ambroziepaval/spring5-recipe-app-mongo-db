package com.ambroziepaval.spring5recipeapp.repositories.reactive;

import com.ambroziepaval.spring5recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class CategoryReactiveRepositoryTest {

    @Autowired
    private CategoryReactiveRepository categoryReactiveRepository;

    @BeforeEach
    void setUp() {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    void testSave() {
        Category category = new Category();
        category.setDescription("Test category");

        categoryReactiveRepository.save(category).block();

        Long count = categoryReactiveRepository.count().block();

        assertEquals(Long.valueOf(1L), count);
    }

    @Test
    void findByDescription() {
        String CATEGORY_DESCRIPTION = "Test category";

        Category category = new Category();
        category.setDescription(CATEGORY_DESCRIPTION);

        categoryReactiveRepository.save(category).block();

        Category fetchedCategory = categoryReactiveRepository.findByDescription(CATEGORY_DESCRIPTION).block();

        assertNotNull(fetchedCategory);
    }
}