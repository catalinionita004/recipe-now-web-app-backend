package com.example.recipenowwebappbackend.entity;

import com.example.recipenowwebappbackend.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tag")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Tag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;
}
