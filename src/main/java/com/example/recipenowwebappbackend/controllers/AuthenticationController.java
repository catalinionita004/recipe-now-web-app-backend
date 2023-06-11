package com.example.recipenowwebappbackend.controllers;


import com.example.recipenowwebappbackend.dtos.ApiResponse;
import com.example.recipenowwebappbackend.exceptions.UserException;
import com.example.recipenowwebappbackend.manager.JWTAuthenticationManager;
import com.example.recipenowwebappbackend.models.auth.AuthenticationRequest;
import com.example.recipenowwebappbackend.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private JWTAuthenticationManager jwtAuthenticationManager;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResponse login(@RequestBody AuthenticationRequest authenticationRequest) throws UserException {
        System.out.println(authenticationRequest);


        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User logged in successfully.",jwtAuthenticationManager.login(authenticationRequest));
    }

    @GetMapping("/current")
    public ApiResponse getCurrentUser() {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Current user returned successfully.",(userService.getCurrentUser()));
    }

}
