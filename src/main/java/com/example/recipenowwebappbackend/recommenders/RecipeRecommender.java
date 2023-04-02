package com.example.recipenowwebappbackend.recommenders;

import com.example.recipenowwebappbackend.models.Recipe;
import com.example.recipenowwebappbackend.models.UserPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeRecommender {

    private List<Recipe> allRecipes;
    private UserPreferences userPreferences;

    public RecipeRecommender(List<Recipe> allRecipes, UserPreferences userPreferences) {
        this.allRecipes = allRecipes;
        this.userPreferences = userPreferences;
    }

//    public List<Recipe> recommendRecipes() {
//        Map<Recipe, Double> recipeScores = new HashMap<>();
//
//        for (Recipe recipe : allRecipes) {
//            double score = 0;
//
//            // Check favorite ingredients
//            for (String ingredient : recipe.getIngredients()) {
//                if (userPreferences.getFavoriteIngredients().contains(ingredient)) {
//                    score += 10;
//                }
//            }
//
//            // Check excluded ingredients
//            for (String ingredient : recipe.getIngredients()) {
//                if (userPreferences.getExcludedIngredients().contains(ingredient)) {
//                    score = -1;
//                    break;
//                }
//            }
//
//            // Check if user prefers healthy food
//            if (userPreferences.prefersHealthyFood() && recipe.isHealthy()) {
//                score += 5;
//            }
//
//            // Check if user prefers spicy food
//            if (userPreferences.prefersSpicyFood() && recipe.isSpicy()) {
//                score += 5;
//            }
//
//            // Check dietary requirements
//            for (String requirement : userPreferences.getDietaryRequirements().keySet()) {
//                if (userPreferences.getDietaryRequirements().get(requirement) && !recipe.getDietaryInfo().contains(requirement)) {
//                    score = -1;
//                    break;
//                }
//            }
//
//            // Check max preparation time
//            if (recipe.getPreparationTime() <= userPreferences.getMaxPreparationTime()) {
//                score += 10;
//            }
//
//            // Check favorite cuisines
//            if (userPreferences.getFavoriteCuisines().contains(recipe.getCuisine())) {
//                score += 5;
//            }
//
//            // Check favorite dishes
//            if (userPreferences.getFavoriteDishes().contains(recipe.getDishName())) {
//                score += 20;
//            }
//
//            if (score > 0) {
//                recipeScores.put(recipe, score);
//            }
//        }
//
//        List<Recipe> recommendedRecipes = new ArrayList<>(recipeScores.keySet());
//        recommendedRecipes.sort((recipe1, recipe2) -> Double.compare(recipeScores.get(recipe2), recipeScores.get(recipe1)));
//
//        return recommendedRecipes;
//    }
}
