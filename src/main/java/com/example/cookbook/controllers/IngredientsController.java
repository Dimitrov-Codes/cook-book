package com.example.cookbook.controllers;

import com.example.cookbook.domain.Ingredient;
import com.example.cookbook.domain.Recipe;
import com.example.cookbook.dto.IngredientDTO;
import com.example.cookbook.repositories.IngredientRepository;
import com.example.cookbook.repositories.RecipeRepository;
import com.example.cookbook.repositories.UnitOfMeasureRepository;
import com.example.cookbook.services.IngredientService;
import com.example.cookbook.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class IngredientsController {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    IngredientsController(RecipeRepository recipeRepository, IngredientRepository ingredientRepository,
                          UnitOfMeasureRepository unitOfMeasureRepository, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @RequestMapping("/viewIngredients/{recipeId}")
    public String viewAllIngredients(@PathVariable String recipeId, Model model) {
        Set<Ingredient> ingredients = recipeRepository.findById(Long.parseLong(recipeId)).get().getIngredients();
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("recipeId", recipeId);
        return "ingredient/ingredientsView";
    }

    @RequestMapping("/createIngredient/{recipeId}")
    public String createIngredient(@PathVariable String recipeId, Model model) {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setRecipeId(Long.parseLong(recipeId));
        model.addAttribute("ingredient", ingredientDTO);
        model.addAttribute("units", unitOfMeasureRepository.findAll().stream().map(unitOfMeasureService::convertToDTO).collect(Collectors.toList()));
        return "ingredient/editIngredient";
    }

    @RequestMapping("/updateIngredient/{id}")
    public String updateIngredient(@PathVariable String id, Model model) {
        IngredientDTO dto = ingredientService.findDtoById(Long.parseLong(id));
        if (dto == null) return "error";
        model.addAttribute("ingredient", dto);
        model.addAttribute("units", unitOfMeasureRepository.findAll().stream().map(unitOfMeasureService::convertToDTO).collect(Collectors.toList()));
        return "ingredient/editIngredient";
    }

    @RequestMapping("/deleteIngredient/{ingredientId}&&{recipeId}")
    public String deleteIngredient(@PathVariable String ingredientId, @PathVariable String recipeId,
                                   Model model) {
        ingredientService.deleteById(Long.parseLong(ingredientId), Long.parseLong(recipeId));
        return "redirect:/viewIngredients/" + recipeId;
    }

    @PostMapping("/addOrUpdateIngredient")
    public String saveOrUpdateIngredient(@ModelAttribute IngredientDTO ingredientDTO) {
        Ingredient detachedIngredient = ingredientService.convertToIngredient(ingredientDTO);
        ingredientRepository.save(detachedIngredient);
        return "redirect:/viewIngredients/" + ingredientDTO.getRecipeId();
    }

}
