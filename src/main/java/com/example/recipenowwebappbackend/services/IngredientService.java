package com.example.recipenowwebappbackend.services;

import com.example.recipenowwebappbackend.dtos.IngredientDto;
import com.example.recipenowwebappbackend.exceptions.ResourceNotFoundException;
import com.example.recipenowwebappbackend.mappers.IngredientMapper;
import com.example.recipenowwebappbackend.models.Ingredient;
import com.example.recipenowwebappbackend.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


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


    public List<IngredientDto> findIngredientByName(String name) {
        return ingredientMapper.modelsToDtos(new HashSet<>(ingredientRepository.findByNameStartingWith(name, PageRequest.of(0, 10))));
    }
}
