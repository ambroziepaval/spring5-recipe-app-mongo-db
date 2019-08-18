package com.ambroziepaval.spring5recipeapp.repositories;

import com.ambroziepaval.spring5recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
