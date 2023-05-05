package com.example.recipenowwebappbackend.controllers;


import com.example.recipenowwebappbackend.dtos.IngredientDto;
import com.example.recipenowwebappbackend.dtos.RecipeDto;
import com.example.recipenowwebappbackend.models.Interaction;
import com.example.recipenowwebappbackend.models.Recipe;
import com.example.recipenowwebappbackend.repositories.InteractionRepository;
import com.example.recipenowwebappbackend.services.impl.ImportService;
import com.example.recipenowwebappbackend.services.impl.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;


    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable Long id) {
        return new ResponseEntity<>(recipeService.findRecipeById(id), HttpStatus.FOUND);
    }

    @GetMapping("/page/{pageNumber}")
    public ResponseEntity<List<RecipeDto>> findAllRecommendedRecipes(@PathVariable int pageNumber) {
        List<RecipeDto> recipePage = recipeService.findAllRecommendedRecipes(10,pageNumber);
        return new ResponseEntity<>(recipePage, HttpStatus.FOUND);
    }


}
