package com.example.cookbook.repositories;

import com.example.cookbook.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository  extends CrudRepository<Ingredient, Long> {
}
