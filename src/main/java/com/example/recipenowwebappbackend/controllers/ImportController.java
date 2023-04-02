package com.example.recipenowwebappbackend.controllers;


import com.example.recipenowwebappbackend.models.AuthenticationRequest;
import com.example.recipenowwebappbackend.models.Interaction;
import com.example.recipenowwebappbackend.repositories.InteractionRepository;
import com.example.recipenowwebappbackend.services.impl.ImportService;
import com.example.recipenowwebappbackend.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ImportController {

    @Autowired
    private ImportService importService;

    @Autowired
    private InteractionRepository interactionRepository;

    @PostMapping("/import")
    public List<Interaction> importData(@RequestBody Long id) {
        return interactionRepository.findInteractionsByUserId(id);

    }

}
