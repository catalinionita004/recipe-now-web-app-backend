package com.example.recipenowwebappbackend.mappers;

import com.example.recipenowwebappbackend.dtos.RecipeStepDto;
import com.example.recipenowwebappbackend.mappers.base.BaseMapper;
import com.example.recipenowwebappbackend.models.RecipeStep;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecipeStepMapper implements BaseMapper<RecipeStepDto, RecipeStep> {


    @Override
    public RecipeStepDto modelToDto(RecipeStep recipeStep) {
        return new RecipeStepDto(
                recipeStep.getId(),
                recipeStep.getRecipe().getId(),
                recipeStep.getStepNumber(),
                recipeStep.getStepDescription()
        );
    }

    @Override
    public RecipeStep dtoToModel(RecipeStepDto recipeStepDto) {
        return null;
    }

    @Override
    public List<RecipeStepDto> modelsToDtos(Set<RecipeStep> recipeSteps) {
        return recipeSteps.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    @Override
    public List<RecipeStep> dtosToModels(List<RecipeStepDto> recipeStepDtos) {
        return null;
    }
}
