package com.example.cookbook.services;

import com.example.cookbook.domain.Difficulty;
import com.example.cookbook.domain.Recipe;
import com.example.cookbook.dto.RecipeDTO;
import com.example.cookbook.repositories.RecipeRepository;
import jakarta.persistence.EntityManager;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    public final RecipeRepository recipeRepository;
    private final NotesService notesService;
    private final CategoryService categoryService;
    private final IngredientService ingredientService;

    public RecipeServiceImpl(RecipeRepository recipeRepository, NotesService notesService,
                             CategoryService categoryService, IngredientService ingredientService) {
        this.recipeRepository = recipeRepository;
        this.notesService = notesService;
        this.categoryService = categoryService;
        this.ingredientService = ingredientService;
    }

    @Override
    public List<RecipeDTO> getDTO() {
        return recipeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Recipe saveRecipeDTO(RecipeDTO recipeDTO) {
        Recipe detachedRecipe = convertToRecipe(recipeDTO);
        return recipeRepository.save(detachedRecipe);
    }


    @Override
    public Set<Recipe> getRecipes() {
        log.debug("In RecipeServiceImpl");
        return new HashSet<>(recipeRepository.findAll());
    }
    @Synchronized
    public Recipe convertToRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe();
        recipe.setId(recipeDTO.getId());
        recipe.setCookTime(recipeDTO.getCookTime());
        recipe.setPrepTime(recipeDTO.getPrepTime());
        recipe.setDescription(recipeDTO.getDescription());
        recipe.setDifficulty(Difficulty.valueOf(recipeDTO.getDifficulty()));
        recipe.setDirections(recipeDTO.getDirections());
        recipe.setServings(recipeDTO.getServings());
        recipe.setSource(recipeDTO.getSource());
        recipe.setUrl(recipeDTO.getUrl());
        recipe.setNotes(notesService.convertToNotes(recipeDTO.getNotes()));

        if (recipeDTO.getIngredients() != null && recipeDTO.getIngredients().size() > 0) {
            recipeDTO.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(ingredientService.convertToIngredient(ingredient)));
        }
        if (recipeDTO.getCategories() != null && recipeDTO.getCategories().size() > 0) {
            recipeDTO.getCategories()
                    .forEach( category -> recipe.getCategories().add(categoryService.convertToCategory(category)));
        }
        return recipe;
    }
    @Synchronized
    public RecipeDTO convertToDTO(Recipe recipe) {
        RecipeDTO dto = new RecipeDTO();
        dto.setId(recipe.getId());
        dto.setCookTime(recipe.getCookTime());
        dto.setPrepTime(recipe.getPrepTime());
        dto.setDescription(recipe.getDescription());
        dto.setDifficulty(recipe.getDifficulty().toString());
        dto.setDirections(recipe.getDirections());
        dto.setServings(recipe.getServings());
        dto.setSource(recipe.getSource());
        dto.setUrl(recipe.getUrl());
        dto.setNotes(notesService.convertToDTO(recipe.getNotes()));
        dto.setCategories((recipe.getCategories().stream().map(categoryService::convertToDTO).collect(Collectors.toSet())));
        dto.setIngredients(recipe.getIngredients().stream().map(ingredientService::convertToDTO).collect(Collectors.toSet()));
        return dto;
    }
    @Synchronized
    @Transactional
    @Override
    public RecipeDTO findDtoById(Long id) {
        return convertToDTO(recipeRepository.findById(id).orElse(null));

    }
}
