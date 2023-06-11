package com.example.recipenowwebappbackend.mappers;


import com.example.recipenowwebappbackend.dtos.IngredientDto;
import com.example.recipenowwebappbackend.mappers.base.BaseMapper;
import com.example.recipenowwebappbackend.models.Ingredient;
import com.example.recipenowwebappbackend.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class IngredientMapper implements BaseMapper<IngredientDto, Ingredient> {


    @Autowired
    IngredientRepository ingredientRepository;

    @Override
    public IngredientDto modelToDto(Ingredient ingredient) {
        return new IngredientDto(
                ingredient.getId(),
                ingredient.getName()
        );
    }

    @Override
    public Ingredient dtoToModel(IngredientDto ingredientDto) {
        if (ingredientDto.getId() != null) {
            Optional<Ingredient> optionalIngredient = ingredientRepository.findById(ingredientDto.getId());
            if (optionalIngredient.isPresent())
                return optionalIngredient.get();
        }
        return new Ingredient(ingredientDto.getName());
    }

    @Override
    public List<IngredientDto> modelsToDtos(Set<Ingredient> ingredients) {
        return ingredients.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    @Override
    public Set<Ingredient> dtosToModels(List<IngredientDto> ingredientDtos) {
        return ingredientDtos.stream().map(this::dtoToModel).collect(Collectors.toSet());
    }
}
