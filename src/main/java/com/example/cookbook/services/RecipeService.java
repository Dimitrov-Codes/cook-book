package com.example.cookbook.services;

import com.example.cookbook.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    public Set<Recipe> getRecipes();
}
