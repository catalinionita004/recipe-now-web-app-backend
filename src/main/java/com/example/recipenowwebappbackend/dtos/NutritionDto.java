package com.example.recipenowwebappbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NutritionDto {
    private Long id;
    private Long recipeID;
    private Double calories;
    private Double fat;
    private Double saturatedFat;
    private Double carbohydrates;
    private Double fiber;
    private Double protein;
    private Double sodium;
}
