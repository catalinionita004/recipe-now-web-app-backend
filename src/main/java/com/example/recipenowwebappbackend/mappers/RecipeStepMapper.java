package com.example.recipenowwebappbackend.mappers;

import com.example.recipenowwebappbackend.dtos.RecipeStepDto;
import com.example.recipenowwebappbackend.mappers.base.BaseMapper;
import com.example.recipenowwebappbackend.models.Recipe;
import com.example.recipenowwebappbackend.models.RecipeStep;
import com.example.recipenowwebappbackend.models.Tag;
import com.example.recipenowwebappbackend.repositories.RecipeStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecipeStepMapper implements BaseMapper<RecipeStepDto, RecipeStep> {


    @Autowired
    RecipeStepRepository recipeStepRepository;
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
        return new RecipeStep(
                recipeStepDto.getId(),
                null,
                recipeStepDto.getStepNumber(),
                recipeStepDto.getStepDescription()
        );
    }

    @Override
    public List<RecipeStepDto> modelsToDtos(Set<RecipeStep> recipeSteps) {
        return recipeSteps.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    @Override
    public Set<RecipeStep> dtosToModels(List<RecipeStepDto> recipeStepDtos) {
        return recipeStepDtos.stream().map(this::dtoToModel).collect(Collectors.toSet());
    }
}
