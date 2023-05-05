package com.example.recipenowwebappbackend.controllers;

import com.example.recipenowwebappbackend.exceptions.UserAlreadyExistException;
import com.example.recipenowwebappbackend.models.auth.RegistrationRequest;
import com.example.recipenowwebappbackend.services.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;
import java.util.logging.Level;

@RestController
@CrossOrigin
@AllArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final Logger logger = Logger.getLogger(RegistrationController.class.getName());

    @PostMapping(value = "/register")
    public ResponseEntity<Object> createRegistrationRequest(@RequestBody RegistrationRequest registrationRequest) throws UserAlreadyExistException {
        logger.log(Level.INFO, "new user registered " + registrationRequest);
        return new ResponseEntity<>(userService.register(registrationRequest), HttpStatus.OK);
    }
}
