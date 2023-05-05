package com.example.recipenowwebappbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InteractionDto {
    private Long id;
    private Long userID;
    private Long recipeID;
    private Date date;
    private Integer rating;
    private String review;
}
