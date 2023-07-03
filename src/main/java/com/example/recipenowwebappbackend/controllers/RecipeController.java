package com.example.recipenowwebappbackend.controllers;


import com.example.recipenowwebappbackend.dtos.ApiResponse;
import com.example.recipenowwebappbackend.dtos.RecipeDto;
import com.example.recipenowwebappbackend.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/{id}")
    public ApiResponse getRecipeById(@PathVariable Long id) {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "FIND recipe by id successfully", (recipeService.findRecipeById(id)));
    }

    @GetMapping("/find-all-recommended")
    public ApiResponse findAllRecommendedRecipes(Pageable page) {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "FIND ALL recommended recipes", (recipeService.findAllRecommendedRecipes(page)));
    }

    @GetMapping("/find-reviewed-recipes")
    public ApiResponse findReviewedRecipes(Pageable page) {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "FIND ALL reviewed recipes", (recipeService.findReviewedRecipes(page)));
    }

    @PostMapping
    public ApiResponse postRecipe(@RequestBody RecipeDto recipeDto) {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "POSTED recipe successfully", (recipeService.saveRecipe(recipeDto)));
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteRecipe(@PathVariable Long id) {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "DELETED recipe successfully", (recipeService.deleteRecipe(id)));
    }

    @GetMapping("/user")
    public ApiResponse getRecipesByUser(@RequestParam Long id) {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "FIND recipes for user successfully", recipeService.findAllRecipesByUser(id));
    }

    @GetMapping("/search")
    public ApiResponse searchRecipes(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "minMinutes", required = false) Integer minMinutes,
            @RequestParam(value = "maxMinutes", required = false) Integer maxMinutes,
            @RequestParam(value = "ingredient", required = false) List<Long> ingredientDtos,
            @RequestParam(value = "tag", required = false) List<Long> tagDtos,
            @RequestParam(value = "sort", defaultValue = "name") String sort,
            @RequestParam(value = "order", defaultValue = "asc") String order,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Sort.Direction direction = Sort.Direction.fromString(order);
        Sort sortObj = Sort.by(direction, sort);
        Pageable pageable = PageRequest.of(page, size, sortObj);

        if (ingredientDtos == null) {
            ingredientDtos = new ArrayList<>();
        }

        if (tagDtos == null) {
            tagDtos = new ArrayList<>();
        }


        System.out.println(ingredientDtos);
        System.out.println(tagDtos);

        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Search recipes for specific criteria", recipeService.searchRecipes(name,minMinutes,maxMinutes, ingredientDtos, tagDtos,pageable));
    }



}
