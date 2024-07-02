package com.example.recipenowwebappbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recipe")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "minutes", nullable = false)
    private Integer minutes;

    @Column(name = "submitted", nullable = false)
    private LocalDateTime submitted;

    @Column(name = "edit_date", nullable = false)
    private LocalDateTime editDate;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<Interaction> interactions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<RecipeStep> recipeSteps = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "recipe", cascade = CascadeType.ALL)
    private Nutrition nutrition;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "recipe_ingredient",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "recipe_tag",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();


    public Recipe(Long id) {
        this.id = id;
    }

    public Recipe(String name, Integer minutes, LocalDateTime submitted, String description) {
        this.name = name;
        this.minutes = minutes;
        this.submitted = submitted;
        this.description = description;
    }

    public Recipe(Long id, String name, Integer minutes, LocalDateTime submitted, LocalDateTime editDate, String description) {
        this.id = id;
        this.name = name;
        this.minutes = minutes;
        this.submitted = submitted;
        this.editDate = editDate;
        this.description = description;
    }

    public Recipe(Long id, String name, Integer minutes, LocalDateTime submitted, LocalDateTime editDate, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.minutes = minutes;
        this.submitted = submitted;
        this.editDate = editDate;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
        if (nutrition != null) {
            nutrition.setRecipe(this);
        }
    }

    public void setInteractions(Set<Interaction> interactions) {
        for (Interaction interaction : interactions) {
            interaction.setRecipe(this);
        }
        this.interactions = interactions;
    }

    public void setRecipeSteps(Set<RecipeStep> recipeSteps) {
        for (RecipeStep recipeStep : recipeSteps) {
            recipeStep.setRecipe(this);
        }
        this.recipeSteps = recipeSteps;
    }

    public void deleteRecipeStep(RecipeStep recipeStep) {
        recipeSteps.remove(recipeStep);
    }

}

