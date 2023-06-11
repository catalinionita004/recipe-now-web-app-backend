package com.example.recipenowwebappbackend.repositories;

import com.example.recipenowwebappbackend.models.Ingredient;
import com.example.recipenowwebappbackend.models.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface NutritionRepository extends PagingAndSortingRepository<Nutrition, Long>, JpaRepository<Nutrition, Long> {

}
