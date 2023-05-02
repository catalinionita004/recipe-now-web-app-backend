package com.example.recipenowwebappbackend.service;


import com.example.recipenowwebappbackend.dto.base.request.RefreshTokenRequest;
import com.example.recipenowwebappbackend.entity.RefreshToken;

public interface RefreshTokenService {
    RefreshToken findRefreshTokenByRefreshToken(String refreshToken);

    RefreshToken createRefreshToken(String email);

    RefreshToken refreshToken(RefreshTokenRequest refreshTokenRequest);

    Boolean deleteRefreshToken(String email);
}
