package com.example.recipenowwebappbackend.entity;

import com.example.recipenowwebappbackend.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipe")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Recipe extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "minutes", nullable = false)
    private Integer minutes;

    @Column(name = "submitted", nullable = false)
    private Date submitted;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe", cascade = {CascadeType.MERGE})
    private Set<Interaction> interactions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe", cascade = {CascadeType.MERGE})
    private Set<RecipeStep> recipeSteps;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "recipe", cascade = {CascadeType.MERGE})
    private Nutrition nutrition;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "recipe_ingredient",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "recipe_tag",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

}
