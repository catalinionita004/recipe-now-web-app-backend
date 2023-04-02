package com.example.recipenowwebappbackend.services.impl;

import com.example.recipenowwebappbackend.models.Recipe;
import com.example.recipenowwebappbackend.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeService {

        @Autowired
        private RecipeRepository recipeRepository;

        public Optional<Recipe> findById(Long id){
            return recipeRepository.findById(id);
        }



}
