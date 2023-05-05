package com.example.recipenowwebappbackend.services.impl;

import com.example.recipenowwebappbackend.dtos.IngredientDto;
import com.example.recipenowwebappbackend.exceptions.ResourceNotFoundException;
import com.example.recipenowwebappbackend.mappers.IngredientMapper;
import com.example.recipenowwebappbackend.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IngredientService {
    @Autowired
    IngredientMapper ingredientMapper;

    @Autowired
    IngredientRepository ingredientRepository;


    public IngredientDto findIngredientById(Long id){
        return ingredientMapper.modelToDto(ingredientRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Ingredient not found with id " + id) ));

    }




}
