package com.ambroziepaval.spring5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

@Data
@EqualsAndHashCode()
public class Notes {

    @Id
    private String id;

    private String recipeNotes;
}
