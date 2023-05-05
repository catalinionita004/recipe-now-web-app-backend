package com.example.recipenowwebappbackend.mappers;

import com.example.recipenowwebappbackend.dtos.InteractionDto;
import com.example.recipenowwebappbackend.exceptions.ResourceNotFoundException;
import com.example.recipenowwebappbackend.mappers.base.BaseMapper;
import com.example.recipenowwebappbackend.models.Interaction;
import com.example.recipenowwebappbackend.repositories.RecipeRepository;
import com.example.recipenowwebappbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class InteractionMapper implements BaseMapper<InteractionDto, Interaction> {


    @Autowired
    UserRepository userRepository;
    @Autowired
    RecipeRepository recipeRepository;

    @Override
    public InteractionDto modelToDto(Interaction interaction) {
        return new InteractionDto(
                interaction.getId(),
                interaction.getUser().getId(),
                interaction.getRecipe().getId(),
                interaction.getDate(),
                interaction.getRating(),
                interaction.getReview()
        );
    }

    @Override
    public Interaction dtoToModel(InteractionDto interactionDto) {
        return null;
    }

    @Override
    public List<InteractionDto> modelsToDtos(Set<Interaction> interactions) {
        return interactions.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    @Override
    public List<Interaction> dtosToModels(List<InteractionDto> interactionDtos) {
        return null;
    }
}
