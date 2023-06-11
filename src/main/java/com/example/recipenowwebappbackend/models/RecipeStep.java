package com.example.recipenowwebappbackend.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recipe_steps")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RecipeStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @Column(name = "step_number")
    private int stepNumber;

    @Column(name = "step_description")
    private String stepDescription;

    @Override
    public String toString() {
        return "RecipeStep{" +
                "id=" + id +
                ", recipeID=" + recipe.getId() +
                ", stepNumber=" + stepNumber +
                ", stepDescription='" + stepDescription + '\'' +
                '}';
    }
}
