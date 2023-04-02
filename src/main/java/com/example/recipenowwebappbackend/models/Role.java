package com.example.recipenowwebappbackend.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    VIEWER,
    EDITOR,
    ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
