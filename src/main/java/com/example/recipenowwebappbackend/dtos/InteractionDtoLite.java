package com.example.recipenowwebappbackend.dtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InteractionDtoLite {
    private Long recipeId;
    private Integer rating;
}
