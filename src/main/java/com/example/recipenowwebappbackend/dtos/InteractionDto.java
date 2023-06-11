package com.example.recipenowwebappbackend.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InteractionDto {
    private Long id;
    private UserDto user;
    private Long recipeID;
    private LocalDateTime submitted;
    private LocalDateTime editDate;
    private Integer rating;
    private String review;
    private Double averageRatingForRecipe;

    public InteractionDto(Long id, UserDto user, Long recipeID, LocalDateTime submitted, LocalDateTime editDate, Integer rating, String review) {
        this.id = id;
        this.user = user;
        this.recipeID = recipeID;
        this.submitted = submitted;
        this.editDate = editDate;
        this.rating = rating;
        this.review = review;
    }
}
