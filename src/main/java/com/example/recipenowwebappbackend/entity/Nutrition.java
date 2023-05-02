package com.example.recipenowwebappbackend.entity;


import com.example.recipenowwebappbackend.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recipe_nutrition")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Nutrition extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @Column(name = "calories")
    private Double calories;

    @Column(name = "fat")
    private Double fat;

    @Column(name = "saturated_fat")
    private Double saturatedFat;

    @Column(name = "carbohydrates")
    private Double carbohydrates;

    @Column(name = "fiber")
    private Double fiber;

    @Column(name = "protein")
    private Double protein;

    @Column(name = "sodium")
    private Double sodium;
}
