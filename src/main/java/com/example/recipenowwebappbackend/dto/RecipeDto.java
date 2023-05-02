package com.example.recipenowwebappbackend.dto;


import com.example.recipenowwebappbackend.dto.base.BaseDto;
import com.example.recipenowwebappbackend.entity.Nutrition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto extends BaseDto {
    private Long id;
    private String name;
    private Integer minutes;
    private Date submitted;
    private String description;
    private UserDto user;
    private Set<InteractionDto> interactions;
    private Set<RecipeStepDto> recipeSteps;
    private Nutrition nutrition;
    private Set<IngredientDto> ingredients;
    private Set<TagDto> tags;


}
