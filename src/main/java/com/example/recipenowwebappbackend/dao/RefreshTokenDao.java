package com.example.recipenowwebappbackend.dao;

import com.example.recipenowwebappbackend.entity.RefreshToken;

import java.util.Optional;


public interface RefreshTokenDao {
    Optional<RefreshToken> findRefreshTokenByEmail(String email);

    Optional<RefreshToken> findRefreshTokenByRefreshToken(String refreshToken);

    RefreshToken createRefreshToken(RefreshToken refreshToken);

    RefreshToken updateRefreshToken(RefreshToken refreshToken);

    Boolean deleteRefreshTokenByEmail(String email);
}
