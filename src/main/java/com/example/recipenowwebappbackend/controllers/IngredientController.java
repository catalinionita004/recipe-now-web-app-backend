package com.example.recipenowwebappbackend.controllers;


import com.example.recipenowwebappbackend.dtos.IngredientDto;
import com.example.recipenowwebappbackend.services.impl.IngredientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/ingredient")
public class IngredientController {

    @Autowired
    IngredientService ingredientService;


    @GetMapping("/{id}")
    public ResponseEntity<IngredientDto> getIngredientById(@PathVariable Long id) {
        return new ResponseEntity<>(ingredientService.findIngredientById(id), HttpStatus.FOUND);
    }

}
