package com.example.recipenowwebappbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecipeStepDto {
    private Long id;
    private Long recipeID;
    private int stepNumber;
    private String stepDescription;
}
