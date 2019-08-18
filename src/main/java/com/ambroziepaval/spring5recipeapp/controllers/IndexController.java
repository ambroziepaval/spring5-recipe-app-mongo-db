package com.ambroziepaval.spring5recipeapp.controllers;

import com.ambroziepaval.spring5recipeapp.domain.Category;
import com.ambroziepaval.spring5recipeapp.domain.UnitOfMeasure;
import com.ambroziepaval.spring5recipeapp.repositories.CategoryRepository;
import com.ambroziepaval.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage() {

        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Category ID is: " + categoryOptional.get().getId());
        System.out.println("Unit of Measure ID is: " + unitOfMeasureOptional.get().getId());

        return "index";
    }
}
