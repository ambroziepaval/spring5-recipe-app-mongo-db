package com.ambroziepaval.spring5recipeapp.services;

import com.ambroziepaval.spring5recipeapp.commands.IngredientCommand;
import com.ambroziepaval.spring5recipeapp.converters.IngredientCommandToIngredient;
import com.ambroziepaval.spring5recipeapp.converters.IngredientToIngredientCommand;
import com.ambroziepaval.spring5recipeapp.domain.Ingredient;
import com.ambroziepaval.spring5recipeapp.domain.Recipe;
import com.ambroziepaval.spring5recipeapp.domain.UnitOfMeasure;
import com.ambroziepaval.spring5recipeapp.repositories.RecipeRepository;
import com.ambroziepaval.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (recipeOptional.isEmpty()) {
            // TODO error handling
            log.error("Recipe id not found. Id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientToIngredientCommand::convert)
                .findFirst();

        if (ingredientCommandOptional.isEmpty()) {
            log.error("Ingredient id is not found. Id: " + ingredientId);
        }

        return ingredientCommandOptional.get();
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());

        if (recipeOptional.isEmpty()) {
            log.error("Recipe not found for id: {}", ingredientCommand.getRecipeId());
            return new IngredientCommand();
        }

        Recipe recipe = recipeOptional.get();

        Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                .findFirst();

        if (ingredientOptional.isPresent()) {
            // update existing ingredient
            Ingredient ingredient = ingredientOptional.get();
            ingredient.setDescription(ingredientCommand.getDescription());
            ingredient.setAmount(ingredientCommand.getAmount());

            UnitOfMeasure uom = unitOfMeasureRepository
                    .findById(ingredientCommand.getUom().getId())
                    .orElseThrow(() -> new RuntimeException("Requested UOM not found"));
            ingredient.setUom(uom);
        } else {
            // add new ingredient
            recipe.addIngredient(ingredientCommandToIngredient.convert(ingredientCommand));
        }

        Recipe savedRecipe = recipeRepository.save(recipe);

        Ingredient savedIngredient = savedRecipe.getIngredients().stream()
                .filter(recipeIngredient -> recipeIngredient.getId().equals(ingredientCommand.getId()))
                .findFirst()
                .get();

        return ingredientToIngredientCommand.convert(savedIngredient);
    }
}
