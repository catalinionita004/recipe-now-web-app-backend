package com.example.recipenowwebappbackend.dto.base.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    @NotBlank(message = "Login email cannot be blank")
    @NotNull(message = "Login email cannot be null")
    private String email;

    @NotBlank(message = "Login password cannot be blank")
    @NotNull(message = "Login password cannot be null")
    private String password;

}
