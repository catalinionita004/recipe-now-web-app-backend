package com.example.recipenowwebappbackend.manager;

import com.example.recipenowwebappbackend.dto.base.request.AuthRequest;
import com.example.recipenowwebappbackend.dto.base.request.RefreshTokenRequest;
import com.example.recipenowwebappbackend.dto.base.response.AuthResponse;

public interface JWTAuthenticationManager {
    AuthResponse login(AuthRequest authRequest);

    AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    Boolean logout();

    String getCurrentUserEmail();
}
