package com.example.cookbook.services;

import com.example.cookbook.domain.Recipe;
import com.example.cookbook.dto.RecipeDTO;

import java.util.List;
import java.util.Set;

public interface RecipeService {
    public Set<Recipe> getRecipes();
    public List<RecipeDTO> getDTO();
    public Recipe saveRecipeDTO(RecipeDTO recipeDTO);
    public Recipe convertToRecipe(RecipeDTO recipeDTO);
    public RecipeDTO convertToDTO(Recipe recipe);
    RecipeDTO findDtoById(Long id);
}
