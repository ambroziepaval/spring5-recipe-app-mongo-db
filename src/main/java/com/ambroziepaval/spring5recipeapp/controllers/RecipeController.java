package com.ambroziepaval.spring5recipeapp.controllers;

import com.ambroziepaval.spring5recipeapp.commands.RecipeCommand;
import com.ambroziepaval.spring5recipeapp.exceptions.NotFoundException;
import com.ambroziepaval.spring5recipeapp.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
@AllArgsConstructor
public class RecipeController {

    private static final String RECIPE_MODEL_ATTRIBUTE = "recipe";
    public static final String RECIPE_RECIPEFORM_URL = "recipe/recipeForm";

    private final RecipeService recipeService;

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {

        model.addAttribute(RECIPE_MODEL_ATTRIBUTE, recipeService.findById(id));
        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model) {

        model.addAttribute(RECIPE_MODEL_ATTRIBUTE, new RecipeCommand());
        return RECIPE_RECIPEFORM_URL;
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(Model model, @PathVariable String id) {

        model.addAttribute(RECIPE_MODEL_ATTRIBUTE, recipeService.findCommandById(id));
        return RECIPE_RECIPEFORM_URL;
    }

    @PostMapping("recipe")
    public String saveOrUpdateRecipe(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));

            return RECIPE_RECIPEFORM_URL;
        }

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id) {
        recipeService.deleteById(id);
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
