package com.example.recipenowwebappbackend.repositories;

import com.example.recipenowwebappbackend.models.Nutrition;
import com.example.recipenowwebappbackend.models.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface RecipeStepRepository extends PagingAndSortingRepository<RecipeStep, Long>, JpaRepository<RecipeStep, Long> {

}
