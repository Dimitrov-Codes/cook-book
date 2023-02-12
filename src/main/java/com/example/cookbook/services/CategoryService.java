package com.example.cookbook.services;

import com.example.cookbook.domain.Category;
import com.example.cookbook.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service

public class CategoryService {
    public CategoryDTO convertToDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setDescription(category.getCategoryName());
        categoryDTO.setId(category.getId());
        return categoryDTO;
    }

    public Category convertToCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getDescription());
        category.setId(categoryDTO.getId());
        return category;
    }
}
