package com.ambroziepaval.spring5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode()
public class Ingredient {

    private String id = UUID.randomUUID().toString();
    private String description;
    private BigDecimal amount;
    private UnitOfMeasure uom;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

}
