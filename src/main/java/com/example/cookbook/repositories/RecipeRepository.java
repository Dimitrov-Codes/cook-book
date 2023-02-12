package com.example.cookbook.repositories;

import com.example.cookbook.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
