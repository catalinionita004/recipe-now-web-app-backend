package com.example.recipenowwebappbackend.repositories;

import com.example.recipenowwebappbackend.models.Ingredient;
import com.example.recipenowwebappbackend.models.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface IngredientRepository extends PagingAndSortingRepository<Ingredient, Long>, JpaRepository<Ingredient, Long> {

}
