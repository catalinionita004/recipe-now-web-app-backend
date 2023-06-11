package com.example.recipenowwebappbackend.mappers;

import com.example.recipenowwebappbackend.dtos.RecipeLiteDto;
import com.example.recipenowwebappbackend.mappers.base.BaseMapper;
import com.example.recipenowwebappbackend.models.Recipe;

import java.util.List;
import java.util.Set;

public class RecipeLiteMapper implements BaseMapper<RecipeLiteDto, Recipe> {
    @Override
    public RecipeLiteDto modelToDto(Recipe recipe) {
        return null;
    }

    @Override
    public Recipe dtoToModel(RecipeLiteDto recipeLiteDto) {
        return null;
    }

    @Override
    public List<RecipeLiteDto> modelsToDtos(Set<Recipe> recipes) {
        return null;
    }

    @Override
    public Set<Recipe> dtosToModels(List<RecipeLiteDto> recipeLiteDtos) {
        return null;
    }
}
