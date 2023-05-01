package com.example.recipenowwebappbackend.entity;

import com.henry.bookrecommendationsystem.entity.base.BaseEntity;
import com.henry.bookrecommendationsystem.enums.UserReadingLevel;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;

/**
 * @author Henry Azer
 * @since 10/11/2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_reading_info", schema = "public")
public class UserReadingInfo extends BaseEntity {

    @Id
    @SequenceGenerator(name = "user_reading_info_id_sequence", sequenceName = "user_reading_info_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_reading_info_id_sequence")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "reading_level", nullable = false)
    private UserReadingLevel readingLevel;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {MERGE, PERSIST, DETACH, REFRESH})
    private List<UserBookCategory> userBookCategories;
}
