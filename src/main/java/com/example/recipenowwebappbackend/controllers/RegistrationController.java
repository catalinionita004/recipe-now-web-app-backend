package com.example.recipenowwebappbackend.controllers;

import com.example.recipenowwebappbackend.dtos.ApiResponse;
import com.example.recipenowwebappbackend.exceptions.UserAlreadyExistException;
import com.example.recipenowwebappbackend.models.auth.RegistrationRequest;
import com.example.recipenowwebappbackend.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.logging.Logger;
import java.util.logging.Level;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class RegistrationController {
    private final UserService userService;
    private final Logger logger = Logger.getLogger(RegistrationController.class.getName());

    @PostMapping(value = "/register")
    public ApiResponse createRegistrationRequest(@RequestBody RegistrationRequest registrationRequest) throws UserAlreadyExistException {
        logger.log(Level.INFO, "new user registered " + registrationRequest);

        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Register user successfully", userService.register(registrationRequest));
    }
}
