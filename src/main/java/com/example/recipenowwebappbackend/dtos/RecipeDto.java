package com.example.recipenowwebappbackend.dtos;

import com.example.recipenowwebappbackend.models.Ingredient;
import com.example.recipenowwebappbackend.models.Interaction;
import com.example.recipenowwebappbackend.models.Nutrition;
import com.example.recipenowwebappbackend.models.RecipeStep;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {
    private Long id;
    private String name;
    private Integer minutes;
    private Date submitted;
    private String description;
    private UserDto user;
    private List<InteractionDto> interactions;
    private List<RecipeStepDto> recipeStepList;
    private NutritionDto nutrition;
    private List<IngredientDto> ingredients;
    private List<TagDto> tags;
}
