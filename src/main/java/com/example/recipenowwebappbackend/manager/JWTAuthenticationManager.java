package com.example.recipenowwebappbackend.manager;

import com.example.recipenowwebappbackend.models.auth.AuthenticationRequest;
import com.example.recipenowwebappbackend.models.auth.AuthenticationResponse;

public interface JWTAuthenticationManager {
    AuthenticationResponse login(AuthenticationRequest authRequest);
}
