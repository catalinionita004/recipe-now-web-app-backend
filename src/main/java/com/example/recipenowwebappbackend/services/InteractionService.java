package com.example.recipenowwebappbackend.services;

import com.example.recipenowwebappbackend.dtos.InteractionDto;
import com.example.recipenowwebappbackend.exceptions.ResourceNotFoundException;
import com.example.recipenowwebappbackend.exceptions.UserException;
import com.example.recipenowwebappbackend.mappers.InteractionMapper;
import com.example.recipenowwebappbackend.models.Interaction;
import com.example.recipenowwebappbackend.repositories.InteractionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class InteractionService {
    @Autowired
    UserService userService;

    @Autowired
    InteractionMapper interactionMapper;

    @Autowired
    InteractionRepository interactionRepository;


    public InteractionDto saveInteraction(InteractionDto interactionDto) {

        LocalDateTime localDateTime = LocalDateTime.now();
        if (interactionDto.getId() != null && interactionRepository.existsById(interactionDto.getId())) {
            interactionDto.setEditDate(localDateTime);
            if (!interactionDto.getUser().getId().equals(userService.getCurrentUser().getId()))
                throw new UserException("You dont have permission to update this interaction because you are not the owner");
        } else {
            interactionDto.setSubmitted(localDateTime);
        }
        InteractionDto interactionDtoExtra = interactionMapper.modelToDto(interactionRepository.save(interactionMapper.dtoToModel(interactionDto)));
        interactionDtoExtra.setAverageRatingForRecipe(interactionRepository.getAverageRatingForRecipe(interactionDtoExtra.getRecipeID()));

        //@TODO ACTUALIZARE
        return interactionDtoExtra;

    }

    public InteractionDto deleteInteraction(Long id) {
        Optional<Interaction> interactionOptional = interactionRepository.findById(id);
        if (interactionOptional.isPresent()) {
            Interaction interaction = interactionOptional.get();
            InteractionDto interactionDto = interactionMapper.modelToDto(interaction);
            if (interaction.getUser().getId().equals(userService.getCurrentUser().getId())) {

                //@TODO ACTUALIZARE
                interactionRepository.deleteById(id);
                interactionDto.setAverageRatingForRecipe(interactionRepository.getAverageRatingForRecipe(interactionDto.getRecipeID()));
                return interactionDto;
            } else
                throw new UserException("You dont have permission to delete this interaction because you are not the owner");
        }
        throw new EntityNotFoundException("Interaction not found with id " + id);
    }


    public InteractionDto findInteractionById(Long interactionId) {
        return interactionMapper.modelToDto(interactionRepository.findById(interactionId).orElseThrow(() ->
                new ResourceNotFoundException("Interaction not found with id " + interactionId)));
    }
}
