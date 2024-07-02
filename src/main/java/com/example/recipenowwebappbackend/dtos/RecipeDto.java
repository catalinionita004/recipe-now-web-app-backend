package com.example.recipenowwebappbackend.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecipeDto {
    private Long id;
    private String name;
    private Integer minutes;
    private LocalDateTime submitted;
    private LocalDateTime editDate;
    private String description;
    private String imageUrl;
    private UserDto user;
    private List<InteractionDto> interactions;
    private List<RecipeStepDto> recipeStepList;
    private NutritionDto nutrition;
    private List<IngredientDto> ingredients;
    private List<TagDto> tags;
    private Double averageRating;
    private Integer ratingCount;
}
