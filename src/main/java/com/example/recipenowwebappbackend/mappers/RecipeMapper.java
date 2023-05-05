package com.example.recipenowwebappbackend.mappers;

import com.example.recipenowwebappbackend.dtos.RecipeDto;
import com.example.recipenowwebappbackend.mappers.base.BaseMapper;
import com.example.recipenowwebappbackend.models.Recipe;
import com.example.recipenowwebappbackend.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecipeMapper implements BaseMapper<RecipeDto, Recipe> {

    @Autowired
    UserMapper userMapper;
    @Autowired
    InteractionMapper interactionMapper;
    @Autowired
    RecipeStepMapper recipeStepMapper;
    @Autowired
    NutritionMapper nutritionMapper;
    @Autowired
    IngredientMapper ingredientMapper;
    @Autowired
    TagMapper tagMapper;

    @Override
    public RecipeDto modelToDto(Recipe recipe) {
        return new RecipeDto(
                recipe.getId(),
                recipe.getName(),
                recipe.getMinutes(),
                recipe.getSubmitted(),
                recipe.getDescription(),
                userMapper.modelToDto(recipe.getUser()),
                interactionMapper.modelsToDtos(recipe.getInteractions()),
                recipeStepMapper.modelsToDtos(recipe.getRecipeSteps()),
                nutritionMapper.modelToDto(recipe.getNutrition()),
                ingredientMapper.modelsToDtos(recipe.getIngredients()),
                tagMapper.modelsToDtos(recipe.getTags())
        );
    }

    @Override
    public Recipe dtoToModel(RecipeDto recipeDto) {
        return null;
    }

    @Override
    public List<RecipeDto> modelsToDtos(Set<Recipe> recipes) {
        return recipes.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    @Override
    public List<Recipe> dtosToModels(List<RecipeDto> recipeDtos) {
        return null;
    }
}
