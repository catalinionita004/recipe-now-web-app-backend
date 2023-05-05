package com.example.recipenowwebappbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeStepDto {
    private Long id;
    private Long recipeID;
    private int stepNumber;
    private String stepDescription;
}
