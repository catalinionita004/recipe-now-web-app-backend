package com.example.recipenowwebappbackend.models;

import java.io.Serializable;

public record AuthenticationRequest(String username, String password) implements Serializable {
}
