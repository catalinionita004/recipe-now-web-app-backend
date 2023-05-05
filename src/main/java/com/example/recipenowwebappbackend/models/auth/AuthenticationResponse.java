package com.example.recipenowwebappbackend.models.auth;

import java.io.Serializable;

public record AuthenticationResponse(String jwt) implements Serializable {
}
