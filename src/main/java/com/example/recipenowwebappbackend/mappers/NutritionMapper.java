package com.example.recipenowwebappbackend.mappers;


import com.example.recipenowwebappbackend.dtos.NutritionDto;
import com.example.recipenowwebappbackend.mappers.base.BaseMapper;
import com.example.recipenowwebappbackend.models.Nutrition;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class NutritionMapper implements BaseMapper<NutritionDto, Nutrition> {
    @Override
    public NutritionDto modelToDto(Nutrition nutrition) {
        return new NutritionDto(
                nutrition.getId(),
                nutrition.getRecipe().getId(),
                nutrition.getCalories(),
                nutrition.getFat(),
                nutrition.getSaturatedFat(),
                nutrition.getCarbohydrates(),
                nutrition.getFiber(),
                nutrition.getProtein(),
                nutrition.getSodium()
        );
    }

    @Override
    public Nutrition dtoToModel(NutritionDto nutritionDto) {
        return null;
    }

    @Override
    public List<NutritionDto> modelsToDtos(Set<Nutrition> nutritions) {
        return nutritions.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    @Override
    public List<Nutrition> dtosToModels(List<NutritionDto> nutritionDtos) {
        return null;
    }
}
