package com.example.cookbook.controllers;

import com.example.cookbook.domain.Category;
import com.example.cookbook.domain.Recipe;
import com.example.cookbook.domain.UnitOfMeasure;
import com.example.cookbook.repositories.CategoryRepository;
import com.example.cookbook.repositories.RecipeRepository;
import com.example.cookbook.repositories.UnitOfMeasureRepository;
import com.example.cookbook.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Controller
public class IndexController {
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeService recipeService;
    private final RecipeRepository recipeRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeService recipeService, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/getRecipes")
    public @ResponseBody
    Collection<Recipe> getRecipesREST() {
        System.out.println("IN GET RECIPES");
        return recipeService.getRecipes();
    }

    @RequestMapping("/show/{id}")
    public String viewRecipeById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeRepository.findById(Long.parseLong(id)).orElse(null));
        return "Recipe";
    }

    @RequestMapping({"/", "", "/index"})
    public String getIndex(Model model) {
        log.debug("In Index Controller");
        model.addAttribute("recipes", recipeService.getRecipes());

        Optional<Category> categoryOptional = categoryRepository.findByCategoryName("Arabian");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByUom("ml");
        System.out.println(categoryRepository.findAll());
        System.out.println(unitOfMeasureRepository.findAll());
        System.out.println(categoryOptional.isPresent() ? categoryOptional.get().getCategoryName() + " " + categoryOptional.get().getId() : false);
        System.out.println(unitOfMeasureOptional.isPresent() ? unitOfMeasureOptional.get().getUom() + " " + unitOfMeasureOptional.get().getId() : false);

        return "index";
    }

}
