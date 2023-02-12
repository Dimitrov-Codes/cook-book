package com.example.cookbook.services;

import com.example.cookbook.domain.Ingredient;
import com.example.cookbook.domain.Recipe;
import com.example.cookbook.dto.IngredientDTO;
import com.example.cookbook.repositories.IngredientRepository;
import com.example.cookbook.repositories.RecipeRepository;
import com.example.cookbook.repositories.UnitOfMeasureRepository;
import lombok.Synchronized;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class IngredientService {
    private final UnitOfMeasureService unitOfMeasureService;
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService(UnitOfMeasureService unitOfMeasureService, IngredientRepository ingredientRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureService = unitOfMeasureService;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    public IngredientDTO convertToDTO(Ingredient ingredient) {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setRecipeId(ingredient.getRecipe().getId());
        ingredientDTO.setDescription(ingredient.getDescription());
        ingredientDTO.setId(ingredient.getId());
        ingredientDTO.setAmount(ingredient.getAmount());
        ingredientDTO.setUnitOfMeasure(unitOfMeasureService.convertToDTO(ingredient.getUom()));
        return ingredientDTO;

    }

    public Ingredient convertToIngredient(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription(ingredientDTO.getDescription());
        ingredient.setAmount(ingredientDTO.getAmount());
        ingredient.setId(ingredientDTO.getId());
        ingredient.setUom(unitOfMeasureRepository.findById(ingredientDTO.getUnitOfMeasureId()).get());
        ingredient.setRecipe(recipeRepository.findById(ingredientDTO.getRecipeId()).get());
        return ingredient;
    }

    @Synchronized
    @Transactional
    public IngredientDTO findDtoById(Long id) {
        return convertToDTO(ingredientRepository.findById(id).orElse(null));

    }

    public void deleteById(Long ingredientId, Long recipeId) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            recipe.setIngredients(recipe
                    .getIngredients()
                    .stream()
                    .filter(i -> !Objects.equals(i.getId(), ingredientId))
                    .collect(Collectors.toSet()));
            recipeRepository.save(recipe);
            ingredientRepository.deleteById(ingredientId);
        }
    }
}
