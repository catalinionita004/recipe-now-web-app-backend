package com.example.recipenowwebappbackend.repositories;

import com.example.recipenowwebappbackend.models.Ingredient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
@Repository
public interface IngredientRepository extends PagingAndSortingRepository<Ingredient, Long>, JpaRepository<Ingredient, Long> {

    List<Ingredient> findByNameStartingWith(String name, Pageable pageable);

    Set<Ingredient> findByNameIn(Set<String> names);

}
