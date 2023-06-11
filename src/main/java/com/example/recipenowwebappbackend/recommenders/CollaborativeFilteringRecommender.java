package com.example.recipenowwebappbackend.recommenders;


import com.example.recipenowwebappbackend.dtos.PredictedRatingaUsersDto;
import com.example.recipenowwebappbackend.dtos.RecipeLiteDto;
import com.example.recipenowwebappbackend.dtos.UserDtoLite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CollaborativeFilteringRecommender {

    public List<RecipeLiteDto> recommendedRecipes(Long userIdCurrent, ConcurrentHashMap<Long, ConcurrentHashMap<Long, Integer>> userRatingsMap, ConcurrentHashMap<Long, Double> userAverageRatingMap, ConcurrentHashMap<Long, RecipeLiteDto> recipeLiteDtoMap, ConcurrentHashMap<Long, UserDtoLite> userDtoLiteMap,int NUM_RECOMMENDATIONS,int NUM_NEIGHBOURHOODS,int MIN_VALUE_RECOMMENDATION) {
//        log.info("CollaborativeFilteringRecommender: recommendedRecipes() called - user id: " + userIdCurrent);
//        //ratings=getUserRecipeRatingMap();
//        log.info("CollaborativeFilteringRecommender: recommendedRecipes() gata cu ratings " + userIdCurrent);
//        // averageRating = getUserAverageRatingMap();
//        log.info("CollaborativeFilteringRecommender: recommendedRecipes() gata cu ratings average" + userIdCurrent);
//        //recipes = getRecipesIdList();
//        log.info("CollaborativeFilteringRecommender: recommendedRecipes() gata cu recipes " + userIdCurrent);


        Map<Long, Double> neighbourhoods = getNeighbourhoods(userIdCurrent, userRatingsMap, userAverageRatingMap,NUM_NEIGHBOURHOODS);
        log.info("CollaborativeFilteringRecommender: recommendedRecipes() gata cu  neighbourhoods" + userIdCurrent);
        ConcurrentMap<Long, PredictedRatingaUsersDto> recommendedRecipes = getRecommendations(userIdCurrent, userRatingsMap, recipeLiteDtoMap, userAverageRatingMap, neighbourhoods);
        log.info("CollaborativeFilteringRecommender: recommendedRecipes() gata cu recommendations " + userIdCurrent);


        return sortPredictedRatingsRecipes(recommendedRecipes, recipeLiteDtoMap, userDtoLiteMap,NUM_RECOMMENDATIONS,MIN_VALUE_RECOMMENDATION);
    }

    /**
     * Get the k-nearest neighbourhoods using Pearson:
     * sim(i,j) = numerator / (sqrt(userDenominator^2) * sqrt(otherUserDenominator^2))
     * numerator = sum((r(u,i) - r(u)) * (r(v,i) - r(v)))
     * userDenominator = sum(r(u,i) - r(i))
     * otherUserDenominator = sum(r(v,i) - r(v))
     * r(u,i): rating of the recipe i by the user u
     * r(u): average rating of the user u
     *
     * @param userIdCurrent        current user id
     * @param userRatingsMap       ratings of the user
     * @param userAverageRatingMap average rating of the user
     * @return nearest neighbourhoods
     */
    private Map<Long, Double> getNeighbourhoods(Long userIdCurrent, ConcurrentHashMap<Long, ConcurrentHashMap<Long, Integer>> userRatingsMap, ConcurrentHashMap<Long, Double> userAverageRatingMap,int NUM_NEIGHBOURHOODS) {
        ConcurrentHashMap<Long, Double> neighbourhoods = new ConcurrentHashMap<>();

        double userAverage = userAverageRatingMap.get(userIdCurrent);
        ConcurrentHashMap<Long, Integer> userCurrentRatings = userRatingsMap.get(userIdCurrent);

        userRatingsMap.keySet().parallelStream().forEach(user -> {
            ArrayList<Long> matches = userCurrentRatings.keySet().stream()
                    .filter(recipeId -> userRatingsMap.get(user).containsKey(recipeId))
                    .collect(Collectors.toCollection(ArrayList::new));

            double matchRate = 0;
            if (!matches.isEmpty()) {
                double numerator = 0, userDenominator = 0, otherUserDenominator = 0;

                for (long recipeId : matches) {
                    double u = userCurrentRatings.get(recipeId) - userAverage;
                    double v = userRatingsMap.get(user).get(recipeId) - userAverageRatingMap.get(user);

                    numerator += u * v;
                    userDenominator += u * u;
                    otherUserDenominator += v * v;
                }

                if (userDenominator != 0 && otherUserDenominator != 0) {
                    matchRate = numerator / (Math.sqrt(userDenominator) * Math.sqrt(otherUserDenominator));
                }
            }
            neighbourhoods.put(user, matchRate);
        });



        return neighbourhoods.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .filter(entry -> entry.getValue() > 0)
                .limit(NUM_NEIGHBOURHOODS)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }



    /**
     * Get predictions of each recipe by a user giving some ratings and its neighbourhood:
     * r(u,i) = r(u) + sum(sim(u,v) * (r(v,i) - r(v))) / sum(abs(sim(u,v)))
     * sim(u,v): similarity between u and v users
     * r(u,i): rating of the recipe i by the user u
     * r(u): average rating of the user u
     *
     * @param userIdCurrent        current user id
     * @param userRatingsMap       ratings of the user
     * @param recipeLiteDtoMap     recipes in the database
     * @param userAverageRatingMap average rating of the user
     * @param neighbourhoods       nearest neighbourhoods
     * @return predictions for each recipe
     */
    private ConcurrentMap<Long, PredictedRatingaUsersDto> getRecommendations(Long userIdCurrent, ConcurrentHashMap<Long, ConcurrentHashMap<Long, Integer>> userRatingsMap, ConcurrentHashMap<Long, RecipeLiteDto> recipeLiteDtoMap, ConcurrentHashMap<Long, Double> userAverageRatingMap, Map<Long, Double> neighbourhoods) {
        ConcurrentMap<Long, PredictedRatingaUsersDto> predictedRatings = new ConcurrentHashMap<>();
        // r(u)
        double userAverage = userAverageRatingMap.get(userIdCurrent);

        // Parallelize the outer loop using parallelStream()
        recipeLiteDtoMap.keySet().parallelStream().forEach(recipeId -> {
            if (!userRatingsMap.get(userIdCurrent).containsKey(recipeId)) {
                HashSet<Long> usersIdRecommendedThisRecipe = new HashSet<>();
                // sum(sim(u,v) * (r(v,i) - r(v)))
                double numerator = 0;

                // sum(abs(sim(u,v)))
                double denominator = 0;

                for (Long neighbourhood : neighbourhoods.keySet()) {
                    if (userRatingsMap.get(neighbourhood).containsKey(recipeId)) {
                        double matchRate = neighbourhoods.get(neighbourhood);
                        numerator +=
                                matchRate * (userRatingsMap.get(neighbourhood).get(recipeId) - userAverageRatingMap.get(neighbourhood));
                        denominator += Math.abs(matchRate);
                        usersIdRecommendedThisRecipe.add(neighbourhood);
                    }
                }

                double predictedRating = 0;
                if (denominator > 0) {
                    predictedRating = userAverage + numerator / denominator;
//                    if (predictedRating > 5) {
//                        predictedRating = 5;
//                    }
                }
                predictedRatings.put(recipeId, new PredictedRatingaUsersDto(predictedRating, usersIdRecommendedThisRecipe));
            }
        });

        return predictedRatings;

    }

    private List<RecipeLiteDto> sortPredictedRatingsRecipes(ConcurrentMap<Long, PredictedRatingaUsersDto> predictedRatings, ConcurrentHashMap<Long, RecipeLiteDto> recipeLiteDtoMap, ConcurrentHashMap<Long, UserDtoLite> userDtoLiteMap,int NUM_RECOMMENDATIONS,int MIN_VALUE_RECOMMENDATION) {
        List<RecipeLiteDto> recommendedRecipes = new ArrayList<>();
        Map<Long, PredictedRatingaUsersDto> sortedRecommendations = predictedRatings.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue(Comparator.comparing(PredictedRatingaUsersDto::getPredictedRating))))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        int i = 0;
        for (Map.Entry<Long, PredictedRatingaUsersDto> entry : sortedRecommendations.entrySet()) {
            if (i >= NUM_RECOMMENDATIONS) break;

            if (entry.getValue().getPredictedRating() >= MIN_VALUE_RECOMMENDATION) {
                RecipeLiteDto recipeLiteDto = new RecipeLiteDto(recipeLiteDtoMap.get(entry.getKey()));
                recipeLiteDto.setPredictedValue(entry.getValue().getPredictedRating());
                recipeLiteDto.setUsers(entry.getValue().getUsersId().stream().map(userId -> userDtoLiteMap.get(userId)).collect(Collectors.toSet()));
                recipeLiteDto.setId(entry.getKey());
                recommendedRecipes.add(recipeLiteDto);
                i++;
            }
        }


        return recommendedRecipes;


    }


}
