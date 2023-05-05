package com.example.recipenowwebappbackend.mappers;



import com.example.recipenowwebappbackend.dtos.IngredientDto;
import com.example.recipenowwebappbackend.mappers.base.BaseMapper;
import com.example.recipenowwebappbackend.models.Ingredient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class IngredientMapper implements BaseMapper<IngredientDto, Ingredient> {


    @Override
    public IngredientDto modelToDto(Ingredient ingredient) {
        return new IngredientDto(
          ingredient.getId(),
          ingredient.getName()
        );
    }

    @Override
    public Ingredient dtoToModel(IngredientDto ingredientDto) {
        return new Ingredient(
          ingredientDto.getId(),
          ingredientDto.getName()
        );
    }

    @Override
    public List<IngredientDto> modelsToDtos(Set<Ingredient> ingredients) {
        return ingredients.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    @Override
    public List<Ingredient> dtosToModels(List<IngredientDto> ingredientDtos) {
        return null;
    }
}
