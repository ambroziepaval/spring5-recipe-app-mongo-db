package com.ambroziepaval.spring5recipeapp.services;

import com.ambroziepaval.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.ambroziepaval.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.ambroziepaval.spring5recipeapp.domain.UnitOfMeasure;
import com.ambroziepaval.spring5recipeapp.repositories.UnitOfMeasureRepository;
import com.ambroziepaval.spring5recipeapp.repositories.UnitOfMeasureRepositoryIT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnitOfMeasureServiceImplTest {

    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    private UnitOfMeasureService service;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        service = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    void listAllUoms() {
        //given
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();

        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        unitOfMeasures.add(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);
        unitOfMeasures.add(uom2);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        //when
        Set<UnitOfMeasureCommand> unitOfMeasureCommands = service.listAllUoms();

        //then
        assertEquals(2, unitOfMeasureCommands.size());
        verify(unitOfMeasureRepository, times(1)).findAll();
    }
}