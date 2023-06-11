package com.example.recipenowwebappbackend.repositories;

import com.example.recipenowwebappbackend.models.Ingredient;
import com.example.recipenowwebappbackend.models.RecipeStep;
import com.example.recipenowwebappbackend.models.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
@Repository
public interface TagRepository extends PagingAndSortingRepository<Tag, Long>, JpaRepository<Tag, Long> {
    List<Tag> findByNameStartingWith(String name, Pageable pageable);

    Set<Tag> findByNameIn(Set<String> names);
}
