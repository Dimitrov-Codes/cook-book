package com.example.cookbook.repositories;

import com.example.cookbook.domain.Category;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByCategoryName(String categoryName);
}
