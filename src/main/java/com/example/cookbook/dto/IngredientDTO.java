package com.example.cookbook.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IngredientDTO {
    private Long id;
    private Long recipeId;
    private Long UnitOfMeasureId;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureDTO unitOfMeasure;
}
