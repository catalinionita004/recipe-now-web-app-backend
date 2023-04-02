package com.example.recipenowwebappbackend.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredient")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "ingredients", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Recipe> recipes = new HashSet<>();

}
