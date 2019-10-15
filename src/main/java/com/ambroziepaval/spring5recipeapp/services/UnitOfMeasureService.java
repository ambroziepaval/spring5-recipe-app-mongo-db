package com.ambroziepaval.spring5recipeapp.services;

import com.ambroziepaval.spring5recipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}
