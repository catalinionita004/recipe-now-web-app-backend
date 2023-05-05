package com.example.recipenowwebappbackend.recommenders;


import com.example.recipenowwebappbackend.dtos.RecipeDto;
import com.example.recipenowwebappbackend.models.Recipe;
import com.example.recipenowwebappbackend.repositories.InteractionRepository;
import com.example.recipenowwebappbackend.repositories.RecipeRepository;
import com.example.recipenowwebappbackend.repositories.UserRepository;
import com.example.recipenowwebappbackend.utils.ValueComparator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class CollaborativeFilteringRecommender {


    private static final int NUM_NEIGHBOURHOODS = 10;
    private static final int NUM_RECOMMENDATIONS = 20;
    private static final int MIN_VALUE_RECOMMENDATION = 4;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private InteractionRepository interactionRepository;

    private Map<Long, Map<Long, Integer>> ratings;
    private Map<Long, Double> averageRating;

    private List<Recipe> recommendedRecipes(Long userIdCurrent){
        log.info("CollaborativeFilteringRecommender: recommendedRecipes() called - user id: " + userIdCurrent);
        Map<Long, Double> averageRating = new HashMap<>();
        Map<Long, Map<Long, Integer>> myRatesMap = new TreeMap<>();
        Map<Long, Map<Long, Integer>> userWithRatesMap = new TreeMap<>();

        userRepository.findAll().forEach(userItem -> {
            Long userID = userItem.getId();
            Map<Long, Integer> userRatings = new HashMap<>();

            log.info("rating pentru user "+ userID);
            interactionRepository.findInteractionsByUserId(userID).forEach(interaction -> {
                        if (interaction.getUser().getId().compareTo(userID) == 0) {
                            userRatings.put(interaction.getRecipe().getId(), interaction.getRating());
                        }
                    }
            );

            if (userIdCurrent.compareTo(userID) == 0) {
                myRatesMap.put(userID, userRatings);
            } else {
                userWithRatesMap.put(userID, userRatings);
                setRatings(userWithRatesMap);
                averageRating.put(userID, 0.0);
                for (Map.Entry<Long, Integer> longIntegerEntry : userRatings.entrySet()) {
                    if (ratings.containsKey(userID)) {
                        ratings.get(userID).put(longIntegerEntry.getKey(), longIntegerEntry.getValue());
                        averageRating.put(userID, averageRating.get(userID) + (double) longIntegerEntry.getValue());
                    } else {
                        Map<Long, Integer> recipeRating = new HashMap<>();
                        recipeRating.put(longIntegerEntry.getKey(), longIntegerEntry.getValue());
                        ratings.put(userID, recipeRating);
                        averageRating.put(userID, (double) longIntegerEntry.getValue());
                    }
                }
            }
        });

        log.info("am terminat cu ratingurile");

        for (Map.Entry<Long, Double> longDoubleEntry : averageRating.entrySet()) {
            if (ratings.containsKey(longDoubleEntry.getKey())) {
                longDoubleEntry.setValue(longDoubleEntry.getValue() / (double) ratings.get(longDoubleEntry.getKey()).size());
            }
        }

        setAverageRating(averageRating);
        Map<Long, String> recipes = new HashMap<>();
        recipeRepository.findAll().forEach(recipe -> recipes.put(recipe.getId(), recipe.getName()));

        Map<Long, Double> neighbourhoods = getNeighbourhoods(myRatesMap.get(userIdCurrent));
        Map<Long, Double> recommendations = getRecommendations(myRatesMap.get(userIdCurrent), neighbourhoods, recipes);


        ValueComparator valueComparator = new ValueComparator(recommendations);
        Map<Long, Double> sortedRecommendations = new TreeMap<>(valueComparator);
        sortedRecommendations.putAll(recommendations);

        Iterator<Map.Entry<Long, Double>> sortedREntries = sortedRecommendations.entrySet().iterator();
        List<Recipe> recommendedRecipes = new ArrayList<>();

        int i = 0;
        while (sortedREntries.hasNext() && i < NUM_RECOMMENDATIONS) {
            Map.Entry<Long, Double> entry = sortedREntries.next();
            if (entry.getValue() >= MIN_VALUE_RECOMMENDATION) {
                Optional<Recipe> optionalBook = recipeRepository.findById(entry.getKey());
                optionalBook.ifPresent(recommendedRecipes::add);
                i++;
            }
        }
        log.info("CollaborativeFilteringRecommender: recommendedBooks() ended - user id: " + userIdCurrent);
        return recommendedRecipes;
    }



    public Page<Recipe> recommendedRecipesForPage(Long userId,int noRecipes,int page){

        List<Recipe> recommendedRecipes = this.recommendedRecipes(userId);

        Pageable pageable = PageRequest.of(page, noRecipes);
        Page<Recipe> recommendedRecipesPage = new PageImpl<>(recommendedRecipes, pageable, recommendedRecipes.size());

        return recommendedRecipesPage;
    }


    private void setRatings(Map<Long, Map<Long, Integer>> ratings) {
        this.ratings = ratings;
    }

    private void setAverageRating(Map<Long, Double> averageRating) {
        this.averageRating = averageRating;
    }


    /**
     * Get the k-nearest neighbourhoods using Pearson:
     * sim(i,j) = numerator / (sqrt(userDenominator^2) * sqrt(otherUserDenominator^2))
     * numerator = sum((r(u,i) - r(u)) * (r(v,i) - r(v)))
     * userDenominator = sum(r(u,i) - r(i))
     * otherUserDenominator = sum(r(v,i) - r(v))
     * r(u,i): rating of the book i by the user u
     * r(u): average rating of the user u
     *
     * @param userRatings ratings of the user
     * @return nearest neighbourhoods
     */
    private Map<Long, Double> getNeighbourhoods(Map<Long, Integer> userRatings) {
        Map<Long, Double> neighbourhoods = new HashMap<>();
        ValueComparator valueComparator = new ValueComparator(neighbourhoods);
        Map<Long, Double> sortedNeighbourhoods = new TreeMap<>(valueComparator);

        double userAverage = getAverage(userRatings);
        for (long user : ratings.keySet()) {
            ArrayList<Long> matches = new ArrayList<>();
            for (long recipeId : userRatings.keySet()) {
                if (ratings.get(user).containsKey(recipeId)) {
                    matches.add(recipeId);
                }
            }
            double matchRate;
            if (matches.size() > 0) {
                double numerator = 0, userDenominator = 0, otherUserDenominator = 0;
                for (long recipeId : matches) {
                    double u = userRatings.get(recipeId) - userAverage;
                    double v = ratings.get(user).get(recipeId) - averageRating.get(user);

                    numerator += u * v;
                    userDenominator += u * u;
                    otherUserDenominator += v * v;
                }
                if (userDenominator == 0 || otherUserDenominator == 0) {
                    matchRate = 0;
                } else {
                    matchRate = numerator / (Math.sqrt(userDenominator) * Math.sqrt(otherUserDenominator));
                }
            } else {
                matchRate = 0;
            }

            neighbourhoods.put(user, matchRate);
        }
        sortedNeighbourhoods.putAll(neighbourhoods);
        Map<Long, Double> output = new TreeMap<>();

        Iterator<Map.Entry<Long, Double>> entries = sortedNeighbourhoods.entrySet().iterator();
        int i = 0;
        while (entries.hasNext() && i < NUM_NEIGHBOURHOODS) {
            Map.Entry<Long, Double> entry = entries.next();
            if (entry.getValue() > 0) {
                output.put(entry.getKey(), entry.getValue());
                i++;
            }
        }
        return output;
    }


    /**
     * Get predictions of each book by a user giving some ratings and its neighbourhood:
     * r(u,i) = r(u) + sum(sim(u,v) * (r(v,i) - r(v))) / sum(abs(sim(u,v)))
     * sim(u,v): similarity between u and v users
     * r(u,i): rating of the book i by the user u
     * r(u): average rating of the user u
     *
     * @param userRatings    ratings of the user
     * @param neighbourhoods nearest neighbourhoods
     * @param recipes          books in the database
     * @return predictions for each book
     */
    private Map<Long, Double> getRecommendations(Map<Long, Integer> userRatings, Map<Long, Double> neighbourhoods, Map<Long, String> recipes) {
        Map<Long, Double> predictedRatings = new HashMap<>();

        // r(u)
        double userAverage = getAverage(userRatings);

        for (Long recipeId : recipes.keySet()) {
            if (!userRatings.containsKey(recipeId)) {

                // sum(sim(u,v) * (r(v,i) - r(v)))
                double numerator = 0;

                // sum(abs(sim(u,v)))
                double denominator = 0;

                for (Long neighbourhood : neighbourhoods.keySet()) {
                    if (ratings.get(neighbourhood).containsKey(recipeId)) {
                        double matchRate = neighbourhoods.get(neighbourhood);
                        numerator +=
                                matchRate * (ratings.get(neighbourhood).get(recipeId) - averageRating.get(neighbourhood));
                        denominator += Math.abs(matchRate);
                    }
                }

                double predictedRating = 0;
                if (denominator > 0) {
                    predictedRating = userAverage + numerator / denominator;
                    if (predictedRating > 5) {
                        predictedRating = 5;
                    }
                }
                predictedRatings.put(recipeId, predictedRating);
            }
        }
        return predictedRatings;
    }

    /**
     * Get average of the ratings of a user
     *
     * @param userRatings ratings of a user
     * @return average or the ratings of a user
     */
    private double getAverage(Map<Long, Integer> userRatings) {
        double userAverage = 0;
        for (Map.Entry<Long, Integer> longIntegerEntry : userRatings.entrySet()) {
            userAverage += (int) ((Map.Entry<?, ?>) longIntegerEntry).getValue();
        }
        return userAverage / userRatings.size();
    }




}
