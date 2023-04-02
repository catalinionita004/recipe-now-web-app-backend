package com.example.recipenowwebappbackend.controllers;


import com.example.recipenowwebappbackend.models.Interaction;
import com.example.recipenowwebappbackend.models.Recipe;
import com.example.recipenowwebappbackend.repositories.InteractionRepository;
import com.example.recipenowwebappbackend.services.impl.ImportService;
import com.example.recipenowwebappbackend.services.impl.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/recipes/{id}")
    public Recipe importData(@PathVariable Long id) {

        Optional<Recipe> recipe = recipeService.findById(id);
        return recipe.orElseThrow();
    }

}
