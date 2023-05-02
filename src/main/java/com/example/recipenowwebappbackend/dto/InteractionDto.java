package com.example.recipenowwebappbackend.dto;

import com.example.recipenowwebappbackend.dto.base.BaseDto;
import com.example.recipenowwebappbackend.entity.Recipe;
import com.example.recipenowwebappbackend.entity.User;

import java.util.Date;

public class InteractionDto extends BaseDto {
    private Long id;
    private UserDto user;
    private Recipe recipe;
    private Date date;
    private Integer rating;
    private String review;
}
