package com.example.recipenowwebappbackend.dto;

import com.example.recipenowwebappbackend.dto.base.BaseDto;

public class RecipeStepDto extends BaseDto {
    private Long id;
    private RecipeDto recipe;
    private int stepNumber;
    private String stepDescription;
}
