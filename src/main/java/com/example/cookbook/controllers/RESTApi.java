package com.example.cookbook.controllers;

import com.example.cookbook.dto.RecipeDTO;
import com.example.cookbook.services.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
@RestController
public class RESTApi {
    RecipeService recipeService;

    public RESTApi(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/getRecipes")
    public @ResponseBody
    Collection<RecipeDTO> getRecipesREST() {
        System.out.println("IN GET RECIPES");
        return recipeService.getDTO();
    }
}
