package com.example.recipenowwebappbackend.models;

import java.io.Serializable;

public record AuthenticationResponse(String jwt) implements Serializable {
}
