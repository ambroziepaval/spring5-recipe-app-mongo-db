package com.ambroziepaval.spring5recipeapp.services;

import com.ambroziepaval.spring5recipeapp.commands.IngredientCommand;
import com.ambroziepaval.spring5recipeapp.converters.IngredientCommandToIngredient;
import com.ambroziepaval.spring5recipeapp.converters.IngredientToIngredientCommand;
import com.ambroziepaval.spring5recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.ambroziepaval.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.ambroziepaval.spring5recipeapp.domain.Ingredient;
import com.ambroziepaval.spring5recipeapp.domain.Recipe;
import com.ambroziepaval.spring5recipeapp.repositories.reactive.RecipeReactiveRepository;
import com.ambroziepaval.spring5recipeapp.repositories.reactive.UnitOfMeasureReactiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    private RecipeReactiveRepository recipeReactiveRepository;
    @Mock
    private UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    private IngredientService ingredientService;

    //init converters
    IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient,
                recipeReactiveRepository, unitOfMeasureReactiveRepository);
    }

    @Test
    void findByRecipeIdAndId() {
        // TODO write after error handing is implemented
    }

    @Test
    void findByRecipeIdAndRecipeIdHappyPath() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId("1");

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId("1");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId("2");

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId("3");

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.justOrEmpty(recipeOptional));

        //then
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId("1", "3").block();

        //when
        assertEquals("3", ingredientCommand.getId());
        verify(recipeReactiveRepository, times(1)).findById(anyString());
    }

    @Test
    void testSaveRecipeCommand() {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId("3");
        ingredientCommand.setRecipeId("2");

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId("3");

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.justOrEmpty(recipeOptional));
        when(recipeReactiveRepository.save(any())).thenReturn(Mono.just(savedRecipe));

        //when
        IngredientCommand savedIngredientCommand = ingredientService.saveIngredientCommand(ingredientCommand).block();

        //then
        assertEquals("3", savedIngredientCommand.getId());
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    void testDeleteById() {
        //given
        Recipe recipe = new Recipe();
        Ingredient ingredient = new Ingredient();
        ingredient.setId("3");
        recipe.addIngredient(ingredient);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.justOrEmpty(recipeOptional));
        when(recipeReactiveRepository.save(any(Recipe.class))).thenReturn(Mono.just(recipe));

        //when
        ingredientService.deleteById("1", "3");

        //then
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, times(1)).save(any(Recipe.class));
    }
}
