package com.example.recipenowwebappbackend.entity;

import com.henry.bookrecommendationsystem.entity.base.BaseEntity;
import com.henry.bookrecommendationsystem.enums.UserGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Henry Azer
 * @since 05/11/2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "author", schema = "public")
public class Author extends BaseEntity {

    @Id
    @SequenceGenerator(name = "author_id_sequence", sequenceName = "author_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_sequence")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    @Column(name = "deathdate")
    private Date deathdate;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "gender", nullable = false)
    private UserGender gender;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;
}
