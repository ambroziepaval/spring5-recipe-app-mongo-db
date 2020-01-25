package com.ambroziepaval.spring5recipeapp.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Document
public class Recipe {

    @Id
    private String id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    private String directions;

    private Set<Ingredient> ingredients = new HashSet<>();
    private Byte[] image;
    private Difficulty difficulty;
    private Notes notes;

    private Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes) {

        if (Objects.isNull(notes)) {
            return;
        }

        this.notes = notes;
    }

    public Recipe addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        return this;
    }
}
