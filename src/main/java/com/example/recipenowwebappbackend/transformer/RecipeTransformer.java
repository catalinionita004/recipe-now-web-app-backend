package com.example.recipenowwebappbackend.transformer;

import com.example.recipenowwebappbackend.dto.RecipeDto;
import com.example.recipenowwebappbackend.entity.Recipe;
import com.example.recipenowwebappbackend.transformer.base.BaseTransformer;
import com.example.recipenowwebappbackend.transformer.mapper.RecipeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class RecipeTransformer implements BaseTransformer<Recipe, RecipeDto, RecipeMapper> {
    private final RecipeMapper bookMapper;

    @Override
    public RecipeMapper getMapper() {
        return bookMapper;
    }
}
