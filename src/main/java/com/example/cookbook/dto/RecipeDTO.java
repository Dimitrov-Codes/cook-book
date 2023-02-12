package com.example.cookbook.dto;

import com.example.cookbook.domain.Difficulty;
import com.example.cookbook.domain.Recipe;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RecipeDTO {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<IngredientDTO> ingredients = new HashSet<>();
    private String difficulty;
    private NotesDTO notes;
    private Set<CategoryDTO> categories = new HashSet<>();

}
