package com.example.recipenowwebappbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
