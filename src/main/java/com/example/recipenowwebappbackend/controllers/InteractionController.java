package com.example.recipenowwebappbackend.controllers;


import com.example.recipenowwebappbackend.dtos.ApiResponse;
import com.example.recipenowwebappbackend.dtos.InteractionDto;
import com.example.recipenowwebappbackend.services.InteractionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/interaction")
public class InteractionController {

    @Autowired
    InteractionService interactionService;

    @PostMapping
    public ApiResponse postInteraction(@RequestBody InteractionDto interactionDto){
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "POSTED interaction successfully",(interactionService.saveInteraction(interactionDto)));
    }
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Long id){
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "DELETED interaction successfully",(interactionService.deleteInteraction(id)));
    }
    @GetMapping("/{id}")
    public ApiResponse getInteractionById(@PathVariable Long id) {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "GOT interaction successfully",(interactionService.findInteractionById(id)));
    }
}
