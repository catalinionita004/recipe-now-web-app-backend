package com.example.recipenowwebappbackend.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserPreferences {
    private String userId;
    private List<String> favoriteIngredients;
    private Map<String, Integer> ingredientPreferences;
    private List<String> favoriteCuisines;
    private List<String> favoriteDishes;
    private int maxPreparationTime;
    private Map<String, Boolean> dietaryRequirements;
    private List<String> excludedIngredients;
    private Map<String, Double> cookingSkillLevel;
    private Map<String, Integer> timeConstraints;
    private boolean prefersHealthyFood;
    private boolean prefersSpicyFood;
    private boolean hasAllergies;
    private boolean hasDietaryRestrictions;
    private boolean hasFoodIntolerances;

    public UserPreferences(String userId, List<String> favoriteIngredients, Map<String, Integer> ingredientPreferences,
                           List<String> favoriteCuisines, List<String> favoriteDishes, int maxPreparationTime,
                           Map<String, Boolean> dietaryRequirements, List<String> excludedIngredients,
                           Map<String, Double> cookingSkillLevel, Map<String, Integer> timeConstraints,
                           boolean prefersHealthyFood, boolean prefersSpicyFood, boolean hasAllergies,
                           boolean hasDietaryRestrictions, boolean hasFoodIntolerances) {
        this.userId = userId;
        this.favoriteIngredients = favoriteIngredients;
        this.ingredientPreferences = ingredientPreferences;
        this.favoriteCuisines = favoriteCuisines;
        this.favoriteDishes = favoriteDishes;
        this.maxPreparationTime = maxPreparationTime;
        this.dietaryRequirements = dietaryRequirements;
        this.excludedIngredients = excludedIngredients;
        this.cookingSkillLevel = cookingSkillLevel;
        this.timeConstraints = timeConstraints;
        this.prefersHealthyFood = prefersHealthyFood;
        this.prefersSpicyFood = prefersSpicyFood;
        this.hasAllergies = hasAllergies;
        this.hasDietaryRestrictions = hasDietaryRestrictions;
        this.hasFoodIntolerances = hasFoodIntolerances;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getFavoriteIngredients() {
        return favoriteIngredients;
    }

    public void setFavoriteIngredients(List<String> favoriteIngredients) {
        this.favoriteIngredients = favoriteIngredients;
    }

    public Map<String, Integer> getIngredientPreferences() {
        return ingredientPreferences;
    }

    public void setIngredientPreferences(Map<String, Integer> ingredientPreferences) {
        this.ingredientPreferences = ingredientPreferences;
    }

    public List<String> getFavoriteCuisines() {
        return favoriteCuisines;
    }

    public void setFavoriteCuisines(List<String> favoriteCuisines) {
        this.favoriteCuisines = favoriteCuisines;
    }

    public List<String> getFavoriteDishes() {
        return favoriteDishes;
    }

    public void setFavoriteDishes(List<String> favoriteDishes) {
        this.favoriteDishes = favoriteDishes;
    }

    public int getMaxPreparationTime() {
        return maxPreparationTime;
    }

    public void setMaxPreparationTime(int maxPreparationTime) {
        this.maxPreparationTime = maxPreparationTime;
    }

    public Map<String, Boolean> getDietaryRequirements() {
        return dietaryRequirements;
    }

    public void setDietaryRequirements(Map<String, Boolean> dietaryRequirements) {
        this.dietaryRequirements = dietaryRequirements;
    }

    public List<String> getExcludedIngredients() {
        return excludedIngredients;
    }

    public void setExcludedIngredients(List<String> excludedIngredients) {
        this.excludedIngredients = excludedIngredients;
    }

    public Map<String, Double> getCookingSkillLevel() {
        return cookingSkillLevel;
    }

    public void setCookingSkillLevel(Map<String, Double> cookingSkillLevel) {
        this.cookingSkillLevel = cookingSkillLevel;
    }

    public Map<String, Integer> getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(Map<String, Integer> timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    public boolean prefersHealthyFood() {
        return prefersHealthyFood;
    }

    public void setPrefersHealthyFood(boolean prefersHealthyFood) {
        this.prefersHealthyFood = prefersHealthyFood;
    }

    public boolean prefersSpicyFood() {
        return prefersSpicyFood;
    }

    public void setPrefersSpicyFood(boolean prefersSpicyFood) {
        this.prefersSpicyFood = prefersSpicyFood;
    }

    public boolean hasAllergies() {
        return hasAllergies;
    }

    public void setHasAllergies(boolean hasAllergies) {
        this.hasAllergies = hasAllergies;
    }

    public boolean hasDietaryRestrictions() {
        return hasDietaryRestrictions;
    }

    public void setHasDietaryRestrictions(boolean hasDietaryRestrictions) {
        this.hasDietaryRestrictions = hasDietaryRestrictions;
    }

    public boolean hasFoodIntolerances() {
        return hasFoodIntolerances;
    }

    public void setHasFoodIntolerances(boolean hasFoodIntolerances) {
        this.hasFoodIntolerances = hasFoodIntolerances;
    }

    public void addFavoriteIngredient(String ingredient) {
        if (favoriteIngredients == null) {
            favoriteIngredients = new ArrayList<>();
        }
        favoriteIngredients.add(ingredient);
    }

    public void addIngredientPreference(String ingredient, int preference) {
        if (ingredientPreferences == null) {
            ingredientPreferences = new HashMap<>();
        }
        ingredientPreferences.put(ingredient, preference);
    }

    public void addFavoriteCuisine(String cuisine) {
        if (favoriteCuisines == null) {
            favoriteCuisines = new ArrayList<>();
        }
        favoriteCuisines.add(cuisine);
    }

    public void addFavoriteDish(String dish) {
        if (favoriteDishes == null) {
            favoriteDishes = new ArrayList<>();
        }
        favoriteDishes.add(dish);
    }

    public void addExcludedIngredient(String ingredient) {
        if (excludedIngredients == null) {
            excludedIngredients = new ArrayList<>();
        }
        excludedIngredients.add(ingredient);
    }

    public void addDietaryRequirement(String requirement, boolean isRequired) {
        if (dietaryRequirements == null) {
            dietaryRequirements = new HashMap<>();
        }
        dietaryRequirements.put(requirement, isRequired);
    }

    public void addCookingSkillLevel(String cuisine, double skillLevel) {
        if (cookingSkillLevel == null) {
            cookingSkillLevel = new HashMap<>();
        }
        cookingSkillLevel.put(cuisine, skillLevel);
    }

    public void addTimeConstraint(String constraint, int timeLimit) {
        if (timeConstraints == null) {
            timeConstraints = new HashMap<>();
        }
        timeConstraints.put(constraint, timeLimit);
    }

}


