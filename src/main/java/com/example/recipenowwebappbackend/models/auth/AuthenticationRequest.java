package com.example.recipenowwebappbackend.models.auth;

import java.io.Serializable;

public record AuthenticationRequest(String username, String password) implements Serializable {
}
