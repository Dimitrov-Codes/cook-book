package com.example.cookbook.controllers;

import com.example.cookbook.domain.Recipe;
import com.example.cookbook.dto.RecipeDTO;
import com.example.cookbook.repositories.RecipeRepository;
import com.example.cookbook.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeService recipeService, RecipeRepository recipeRepository) {
        this.recipeService = recipeService;
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping({"/", "", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }

    @RequestMapping("/create")
    public String createRecipe(Model model) {
        model.addAttribute("recipe", new RecipeDTO());
        return "recipe/editRecipe";
    }

    @RequestMapping("/show/{id}")
    public String viewRecipeById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeRepository.findById(Long.parseLong(id)).orElse(null));
        return "recipe/Recipe";
    }

    @RequestMapping("/update/{id}")
    public String updateRecipeById(@PathVariable String id, Model model) {
        RecipeDTO dto = recipeService.findDtoById(Long.parseLong(id));
        if (dto == null) return "error";
        model.addAttribute("recipe", dto);
        return "recipe/editRecipe";
    }

    @PostMapping("/addRecipe")
    public String saveOrUpdate(@ModelAttribute RecipeDTO recipeDTO) {
        Recipe savedRecipe = recipeService.saveRecipeDTO(recipeDTO);
        System.out.println(savedRecipe);
        return "redirect:/show/" + savedRecipe.getId();
    }

    @RequestMapping("/delete/{id}")
    public String deleteRecipeById(@PathVariable String id) {
        recipeRepository.deleteById(Long.parseLong(id));
        return "redirect:/";
    }

}
