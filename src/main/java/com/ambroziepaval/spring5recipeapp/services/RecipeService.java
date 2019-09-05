package com.ambroziepaval.spring5recipeapp.services;

import com.ambroziepaval.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);
}
