package com.example.recipenowwebappbackend.dtos;

import com.example.recipenowwebappbackend.models.Tag;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecipeLiteDto {
    private Long id;
    private String name;
    private Double averageRating;
    private int ratingCount;
    private Double predictedValue;
    private UserDto user;
    private LocalDateTime submitted;
    private LocalDateTime editDate;
    private String imageUrl;

    private Set<UserDtoLite> users;

    private Double popularityScore;

    private Integer minutes;


    public RecipeLiteDto(Long id, String name) {
        this.name = name;
        this.id = id;

    }



    public RecipeLiteDto(String name, Double averageRating, int ratingCount) {
        this.name = name;
        this.averageRating = averageRating;
        this.ratingCount = ratingCount;
        if (this.averageRating == null)
            this.averageRating = 0.0;
    }

    public RecipeLiteDto(Long recipeId, String recipeName, Double averageRating, int ratingCounter) {
        this.id = recipeId;
        this.name = recipeName;
        this.averageRating = averageRating;
        this.ratingCount = ratingCounter;
    }

    public RecipeLiteDto(Long recipeId, String recipeName, Double averageRating, int ratingCounter, UserDto user) {
        this.id = recipeId;
        this.name = recipeName;
        this.averageRating = averageRating;
        this.ratingCount = ratingCounter;
        this.user = user;
        if (this.averageRating == null)
            this.averageRating = 0.0;
    }

    public RecipeLiteDto(Long recipeId, String recipeName, Double averageRating, int ratingCounter,Integer minutes, UserDto user, LocalDateTime submitted, LocalDateTime editDate) {
        this.id = recipeId;
        this.name = recipeName;
        this.averageRating = averageRating;
        this.ratingCount = ratingCounter;
        this.minutes=minutes;
        this.user = user;
        if (this.averageRating == null)
            this.averageRating = 0.0;
        this.submitted = submitted;
        this.editDate = editDate;
        if (editDate == null)
            this.editDate = submitted;
    }

    public RecipeLiteDto(Long recipeId, String recipeName, Double averageRating, int ratingCounter,Integer minutes, UserDto user, LocalDateTime submitted, LocalDateTime editDate,String imageUrl) {
        this.id = recipeId;
        this.name = recipeName;
        this.averageRating = averageRating;
        this.ratingCount = ratingCounter;
        this.minutes=minutes;
        this.user = user;
        if (this.averageRating == null)
            this.averageRating = 0.0;
        this.submitted = submitted;
        this.editDate = editDate;
        if (editDate == null)
            this.editDate = submitted;
        //TODO check if image exist
        this.imageUrl = imageUrl;
    }

    public RecipeLiteDto(RecipeLiteDto recipeLiteDto) {
        this.id = recipeLiteDto.getId();
        this.name = recipeLiteDto.getName();
        this.averageRating = recipeLiteDto.getAverageRating();
        this.ratingCount = recipeLiteDto.getRatingCount();
        this.predictedValue = recipeLiteDto.getPredictedValue();
        this.user = recipeLiteDto.getUser();
        this.submitted = recipeLiteDto.getSubmitted();
        this.editDate = recipeLiteDto.getEditDate();
        this.minutes = recipeLiteDto.getMinutes();
        this.imageUrl = recipeLiteDto.getImageUrl();
    }


    public RecipeLiteDto(RecipeDto recipeDto) {
        this.id= recipeDto.getId();
        this.name = recipeDto.getName();
        this.minutes = recipeDto.getMinutes();
        this.submitted = recipeDto.getSubmitted();
        this.editDate = recipeDto.getEditDate();
        this.user=recipeDto.getUser();
        this.imageUrl = recipeDto.getImageUrl();
    }

}
