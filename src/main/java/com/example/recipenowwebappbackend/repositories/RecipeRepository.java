package com.example.recipenowwebappbackend.repositories;

import com.example.recipenowwebappbackend.dtos.RecipeSearchDto;
import com.example.recipenowwebappbackend.models.Ingredient;
import com.example.recipenowwebappbackend.models.Recipe;
import com.example.recipenowwebappbackend.models.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
@Repository
public interface RecipeRepository extends PagingAndSortingRepository<Recipe, Long>, JpaRepository<Recipe, Long>, JpaSpecificationExecutor<Recipe> {

    @Query("SELECT r.id, r.name, AVG(ri.rating), COUNT(ri.rating),r.minutes, r.submitted, r.editDate, u FROM Recipe r " +
            "LEFT JOIN r.user u " +
            "LEFT JOIN Interaction ri ON r.id = ri.recipe.id " +
            "GROUP BY r.id, r.name, r.submitted, r.editDate, u")
    List<Object[]> findRecipesLite();


    @Query("SELECT AVG(ri.rating), COUNT(ri.rating) FROM Recipe r " +
            "LEFT JOIN Interaction ri ON r.id = ri.recipe.id " +
            "WHERE r.id = :recipeId")
    List<Object[]> findAverageCountRatingByRecipeId(@Param("recipeId") Long recipeId);


    @Query("SELECT r.id, r.name, AVG(ri.rating), COUNT(ri.rating),r.minutes,r.submitted,r.editDate FROM Recipe r " +
            "LEFT JOIN Interaction ri ON r.id = ri.recipe.id " +
            "WHERE r.user.id = :userId " +
            "GROUP BY r.id")
    List<Object[]> findRecipesLiteByUser(@Param("userId") Long userId);


    @Query("SELECT DISTINCT r.id " +
            "FROM Recipe r " +
            "LEFT JOIN r.ingredients i " +
            "LEFT JOIN r.tags t " +
            "WHERE (:name IS NULL OR UPPER(r.name) LIKE UPPER(CONCAT('%', :name, '%'))) " +
            "AND (:minutes IS NULL OR r.minutes = :minutes)")
    List<Long> searchRecipes(@Param("name") String name,
                             @Param("minutes") Integer minutes);


    @Query("SELECT DISTINCT r.id " +
            "FROM Recipe r " +
            "LEFT JOIN r.ingredients i " +
            "LEFT JOIN r.tags t " +
            "WHERE (:name IS NULL OR UPPER(r.name) LIKE UPPER(CONCAT('%', :name, '%'))) " +
            "AND (:minutes IS NULL OR r.minutes = :minutes) " +
            "AND (:ingredients IS NULL OR i.id IN (:ingredients)) ")
    List<Long> searchRecipesIngredients(@Param("name") String name,
                                        @Param("minutes") Integer minutes,
                                        @Param("ingredients") List<Long> ingredients);


    @Query("SELECT DISTINCT r.id " +
            "FROM Recipe r " +
            "LEFT JOIN r.ingredients i " +
            "LEFT JOIN r.tags t " +
            "WHERE (:name IS NULL OR UPPER(r.name) LIKE UPPER(CONCAT('%', :name, '%'))) " +
            "AND (:minutes IS NULL OR r.minutes = :minutes) " +
            "AND (:tags IS NULL OR t.id IN (:tags))")
    List<Long> searchRecipesTags(@Param("name") String name,
                                 @Param("minutes") Integer minutes,
                                 @Param("tags") List<Long> tags);

    @Query("SELECT DISTINCT r.id " +
            "FROM Recipe r " +
            "LEFT JOIN r.ingredients i " +
            "LEFT JOIN r.tags t " +
            "WHERE (:name IS NULL OR UPPER(r.name) LIKE UPPER(CONCAT('%', :name, '%'))) " +
            "AND (:minutes IS NULL OR r.minutes = :minutes) " +
            "AND (:ingredients IS NULL OR i.id IN (:ingredients)) " +
            "AND (:tags IS NULL OR t.id IN (:tags))")
    List<Long> searchRecipes(@Param("name") String name,
                             @Param("minutes") Integer minutes,
                             @Param("ingredients") List<Long> ingredients,
                             @Param("tags") List<Long> tags);

}


