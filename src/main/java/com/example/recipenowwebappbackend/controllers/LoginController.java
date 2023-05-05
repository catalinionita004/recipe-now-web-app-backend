package com.example.recipenowwebappbackend.controllers;


import com.example.recipenowwebappbackend.exceptions.UserException;
import com.example.recipenowwebappbackend.models.auth.AuthenticationRequest;
import com.example.recipenowwebappbackend.models.auth.AuthenticationResponse;
import com.example.recipenowwebappbackend.services.impl.UserService;
import com.example.recipenowwebappbackend.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private UserService userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws UserException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.username(), authenticationRequest.password())
            );
        } catch (BadCredentialsException e) {
            throw new UserException("Incorrect username or password");
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.username());
        return ResponseEntity.ok(new AuthenticationResponse(jwtTokenUtil.generateToken(userDetails)));
    }
}
