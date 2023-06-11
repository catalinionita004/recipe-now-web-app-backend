package com.example.recipenowwebappbackend.dtos;

import lombok.*;

import java.util.HashSet;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PredictedRatingaUsersDto {

    private Double predictedRating;
    private HashSet<Long> usersId;
}
