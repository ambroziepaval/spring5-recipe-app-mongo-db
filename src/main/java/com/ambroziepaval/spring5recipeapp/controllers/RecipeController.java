package com.ambroziepaval.spring5recipeapp.controllers;

import com.ambroziepaval.spring5recipeapp.commands.RecipeCommand;
import com.ambroziepaval.spring5recipeapp.services.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class RecipeController {

    private static final String RECIPE_MODEL_ATTRIBUTE = "recipe";

    private final RecipeService recipeService;

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable Long id, Model model) {

        model.addAttribute(RECIPE_MODEL_ATTRIBUTE, recipeService.findById(id));
        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model) {

        model.addAttribute(RECIPE_MODEL_ATTRIBUTE, new RecipeCommand());
        return "recipe/recipeForm";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(Model model, @PathVariable Long id) {

        model.addAttribute(RECIPE_MODEL_ATTRIBUTE, recipeService.findCommandById(id));
        return "recipe/recipeForm";
    }

    @PostMapping("recipe")
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand command) {

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteById(id);
        return "redirect:/";
    }
}
