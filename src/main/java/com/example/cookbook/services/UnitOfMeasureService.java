package com.example.cookbook.services;

import com.example.cookbook.domain.UnitOfMeasure;
import com.example.cookbook.dto.UnitOfMeasureDTO;
import org.springframework.stereotype.Service;

@Service

public class UnitOfMeasureService {
    public UnitOfMeasureDTO convertToDTO(UnitOfMeasure unitOfMeasure){
        UnitOfMeasureDTO unit = new UnitOfMeasureDTO();
        unit.setDescription(unitOfMeasure.getUom());
        unit.setId(unitOfMeasure.getId());
        return unit;
    }

    public UnitOfMeasure convertToUOM(UnitOfMeasureDTO unitOfMeasure) {
        UnitOfMeasure unit = new UnitOfMeasure();
        unit.setUom(unitOfMeasure.getDescription());
        unit.setId(unitOfMeasure.getId());
        return unit;
    }
}
