package com.ambroziepaval.spring5recipeapp.controllers;

import com.ambroziepaval.spring5recipeapp.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@AllArgsConstructor
@Controller
public class IndexController {

    private final RecipeService recipeService;

    @GetMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage(Model model) {
        log.debug("Getting index page.");

        model.addAttribute("recipes", recipeService.getRecipes().collectList().block());

        return "index";
    }
}
