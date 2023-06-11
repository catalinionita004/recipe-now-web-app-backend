package com.example.recipenowwebappbackend.manager;

import com.example.recipenowwebappbackend.exceptions.UserException;
import com.example.recipenowwebappbackend.models.auth.AuthenticationRequest;
import com.example.recipenowwebappbackend.models.auth.AuthenticationResponse;
import com.example.recipenowwebappbackend.security.utils.JwtUtil;
import com.example.recipenowwebappbackend.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JWTAuthenticationManagerImpl implements JWTAuthenticationManager {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private UserService userDetailsService;

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.username(), authenticationRequest.password())
            );
        } catch (BadCredentialsException e) {
            throw new UserException("Incorrect username or password");
        }
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.username());

        return new AuthenticationResponse(jwtTokenUtil.generateToken(userDetails));
    }
}
