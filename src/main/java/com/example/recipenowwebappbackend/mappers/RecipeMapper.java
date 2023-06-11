package com.example.recipenowwebappbackend.mappers;

import com.example.recipenowwebappbackend.dtos.RecipeDto;
import com.example.recipenowwebappbackend.mappers.base.BaseMapper;
import com.example.recipenowwebappbackend.models.*;
import com.example.recipenowwebappbackend.repositories.RecipeRepository;
import com.example.recipenowwebappbackend.repositories.RecipeStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
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
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    RecipeStepRepository recipeStepRepository;


    @Override
    public RecipeDto modelToDto(Recipe recipe) {
        return new RecipeDto(
                recipe.getId(),
                recipe.getName(),
                recipe.getMinutes(),
                recipe.getSubmitted(),
                recipe.getEditDate(),
                recipe.getDescription(),
                userMapper.modelToDto(recipe.getUser()),
                interactionMapper.modelsToDtos(recipe.getInteractions()),
                recipeStepMapper.modelsToDtos(recipe.getRecipeSteps()),
                nutritionMapper.modelToDto(recipe.getNutrition()),
                ingredientMapper.modelsToDtos(recipe.getIngredients()),
                tagMapper.modelsToDtos(recipe.getTags()),
                recipe.getInteractions().stream()
                .mapToDouble(Interaction::getRating)
                .filter(Objects::nonNull)
                .average()
                .orElse(0.0),
                recipe.getInteractions().size()
        );
    }

    private Recipe initalizeModelFromDto(RecipeDto recipeDto) {
        Recipe recipe;
        if (recipeDto.getId() != null) {
            Optional<Recipe> recipeOptional = recipeRepository.findById(recipeDto.getId());
            if (recipeOptional.isPresent()) {
                recipe = recipeOptional.get();
                recipe.setName(recipeDto.getName());
                recipe.setMinutes(recipeDto.getMinutes());
                recipe.setSubmitted(recipeDto.getSubmitted());
                recipe.setEditDate((recipeDto.getEditDate()));
                recipe.setDescription(recipeDto.getDescription());
                return recipe;
            }
        }

        recipe = new Recipe(
                recipeDto.getId(),
                recipeDto.getName(),
                recipeDto.getMinutes(),
                recipeDto.getSubmitted(),
                recipeDto.getEditDate(),
                recipeDto.getDescription()
        );
        recipe.setUser(userMapper.dtoToModel(recipeDto.getUser()));
        return recipe;

    }


    public Recipe dtoToModel(RecipeDto recipeDto) {

        Recipe recipe = initalizeModelFromDto(recipeDto);

        recipe.setNutrition(nutritionMapper.dtoToModel(recipeDto.getNutrition()));
        recipe.setRecipeSteps(recipeStepMapper.dtosToModels(recipeDto.getRecipeStepList()));
        recipe.setIngredients(ingredientMapper.dtosToModels(recipeDto.getIngredients()));
        recipe.setTags(tagMapper.dtosToModels(recipeDto.getTags()));
        return recipe;
    }

    @Override
    public List<RecipeDto> modelsToDtos(Set<Recipe> recipes) {
        return recipes.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    @Override
    public Set<Recipe> dtosToModels(List<RecipeDto> recipeDtos) {
        return recipeDtos.stream()
                .map(this::dtoToModel)
                .collect(Collectors.toSet());

    }
}
