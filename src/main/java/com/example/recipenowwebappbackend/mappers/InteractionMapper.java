package com.example.recipenowwebappbackend.mappers;

import com.example.recipenowwebappbackend.dtos.InteractionDto;
import com.example.recipenowwebappbackend.dtos.RecipeDto;
import com.example.recipenowwebappbackend.exceptions.UserException;
import com.example.recipenowwebappbackend.mappers.base.BaseMapper;
import com.example.recipenowwebappbackend.models.Interaction;
import com.example.recipenowwebappbackend.models.Recipe;
import com.example.recipenowwebappbackend.models.User;
import com.example.recipenowwebappbackend.repositories.InteractionRepository;
import com.example.recipenowwebappbackend.repositories.RecipeRepository;
import com.example.recipenowwebappbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class InteractionMapper implements BaseMapper<InteractionDto, Interaction> {


    @Autowired
    UserRepository userRepository;
    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    InteractionRepository interactionRepository;
    @Autowired
    UserMapper userMapper;

    @Override
    public InteractionDto modelToDto(Interaction interaction) {
        return new InteractionDto(
                interaction.getId(),
                userMapper.modelToDto(interaction.getUser()),
                interaction.getRecipe().getId(),
                interaction.getSubmitted(),
                interaction.getEditDate(),
                interaction.getRating(),
                interaction.getReview()
        );
    }


    private Interaction initalizeModelFromDto(InteractionDto interactionDto) {
        Interaction interaction;
        if (interactionDto.getId() != null) {
            Optional<Interaction> interactionOptional = interactionRepository.findById(interactionDto.getId());
            if (interactionOptional.isPresent()) {
                interaction = interactionOptional.get();
                interaction.setSubmitted(interactionDto.getSubmitted());
                interaction.setEditDate(interactionDto.getEditDate());
                interaction.setRating(interactionDto.getRating());
                interaction.setReview(interactionDto.getReview());
                return interaction;
            }
        }

        interaction = new Interaction(
                interactionDto.getId(),
                null,
                null,
                interactionDto.getSubmitted(),
                interactionDto.getEditDate(),
                interactionDto.getRating(),
                interactionDto.getReview()
        );
        interaction.setUser(userMapper.dtoToModel(interactionDto.getUser()));

        return interaction;

    }

    @Override
    public Interaction dtoToModel(InteractionDto interactionDto) {
        Interaction interaction = initalizeModelFromDto(interactionDto);
        if (interactionDto.getRecipeID() != null) {
            Optional<Recipe> recipeOptional = recipeRepository.findById(interactionDto.getRecipeID());
            if (recipeOptional.isPresent()) {
                Recipe recipe = recipeOptional.get();
                interaction.setRecipe(recipe);
            }
        }
        return interaction;
    }

    @Override
    public List<InteractionDto> modelsToDtos(Set<Interaction> interactions) {
        return interactions.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    @Override
    public Set<Interaction> dtosToModels(List<InteractionDto> interactionDtos) {
        if (interactionDtos != null)
            return interactionDtos.stream().map(this::dtoToModel).collect(Collectors.toSet());
        else
            return new HashSet<>();
    }
}
