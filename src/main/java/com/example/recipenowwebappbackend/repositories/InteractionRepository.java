package com.example.recipenowwebappbackend.repositories;

import com.example.recipenowwebappbackend.models.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Transactional
@Repository
public interface InteractionRepository extends PagingAndSortingRepository<Interaction, Long>, JpaRepository<Interaction, Long> {
        @Query(value = "SELECT user_id, recipe_id, rating FROM recipe_interaction", nativeQuery = true)
        List<Object[]> findUserRecipeRatings();


        @Query(value = "SELECT user_id, AVG(rating) FROM recipe_interaction GROUP BY user_id", nativeQuery = true)
        List<Object[]> findUserAverageRatings();

        List<Interaction> findInteractionsByUserId(Long id);

        @Query("SELECT AVG(i.rating) FROM Interaction i WHERE i.recipe.id = :recipeId")
        Double getAverageRatingForRecipe(Long recipeId);

}
