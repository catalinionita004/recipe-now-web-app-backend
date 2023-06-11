package com.example.recipenowwebappbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDtoLite {
    private Long id;
    private String firstName;
    private String lastName;
}
