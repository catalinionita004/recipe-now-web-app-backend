package com.example.recipenowwebappbackend.controllers;


import com.example.recipenowwebappbackend.dtos.ApiResponse;
import com.example.recipenowwebappbackend.services.IngredientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/ingredient")
public class IngredientController {

    @Autowired
    IngredientService ingredientService;


    @GetMapping("/{id}")
    public ApiResponse getIngredientById(@PathVariable Long id) {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "FIND ingredient with id" + id, ingredientService.findIngredientById(id));
    }

    @GetMapping("/name")
    public ApiResponse  getIngredientByName(@RequestParam String name) {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "FIND ingredients starting with name" + name, ingredientService.findIngredientByName(name));
    }




}
