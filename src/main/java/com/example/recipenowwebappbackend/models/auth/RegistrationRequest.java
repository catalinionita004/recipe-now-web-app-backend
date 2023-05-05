package com.example.recipenowwebappbackend.models.auth;

import java.io.Serializable;
import java.util.List;

public record RegistrationRequest(String firstName,
                                  String lastName,
                                  String username,
                                  String email,
                                  String password,
                                  String confirmPassword) implements Serializable {

}
