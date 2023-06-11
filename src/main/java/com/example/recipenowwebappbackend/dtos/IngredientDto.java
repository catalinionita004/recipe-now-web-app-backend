package com.example.recipenowwebappbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IngredientDto {
    private Long id;
    private String name;
}
