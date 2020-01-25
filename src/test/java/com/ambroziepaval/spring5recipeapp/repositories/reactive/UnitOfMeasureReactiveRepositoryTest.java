package com.ambroziepaval.spring5recipeapp.repositories.reactive;

import com.ambroziepaval.spring5recipeapp.domain.Category;
import com.ambroziepaval.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class UnitOfMeasureReactiveRepositoryTest {

    public static final String EACH = "Each";
    @Autowired
    private UnitOfMeasureReactiveRepository uomReactiveRepository;

    @BeforeEach
    void setUp() {
        uomReactiveRepository.deleteAll().block();
    }

    @Test
    void testSave() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(EACH);

        uomReactiveRepository.save(unitOfMeasure).block();

        Long count = uomReactiveRepository.count().block();

        assertEquals(Long.valueOf(1L), count);
    }

    @Test
    void findByDescription() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(EACH);

        uomReactiveRepository.save(unitOfMeasure).block();

        UnitOfMeasure fetchedUom = uomReactiveRepository.findByDescription(EACH).block();

        assertNotNull(fetchedUom);
    }
}