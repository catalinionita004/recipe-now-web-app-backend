package com.example.recipenowwebappbackend.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecipeSearchDto {
    private Long id;
    private String name;
    private LocalDateTime submitted;
}
