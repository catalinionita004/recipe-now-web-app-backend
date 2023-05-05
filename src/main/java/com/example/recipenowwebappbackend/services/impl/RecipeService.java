package com.example.recipenowwebappbackend.services.impl;

import com.example.recipenowwebappbackend.dtos.IngredientDto;
import com.example.recipenowwebappbackend.dtos.RecipeDto;
import com.example.recipenowwebappbackend.dtos.UserDto;
import com.example.recipenowwebappbackend.exceptions.ResourceNotFoundException;
import com.example.recipenowwebappbackend.mappers.RecipeMapper;
import com.example.recipenowwebappbackend.models.Recipe;
import com.example.recipenowwebappbackend.models.User;
import com.example.recipenowwebappbackend.recommenders.CollaborativeFilteringRecommender;
import com.example.recipenowwebappbackend.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RecipeMapper recipeMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CollaborativeFilteringRecommender collaborativeFilteringRecommender;


    public RecipeDto findRecipeById(Long id) {
        return recipeMapper.modelToDto(recipeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Recipe not found with id " + id)));

    }

    public List<RecipeDto> findAllRecommendedRecipes(int noRecipes, int page){
        UserDto currentUser = userService
                .getCurrentUser();
        return collaborativeFilteringRecommender
                .recommendedRecipesForPage(currentUser.getId(),noRecipes,page)
                .stream()
                .map(recipeMapper::modelToDto)
                .collect(Collectors.toList());
    }


}
