package com.example.recipenowwebappbackend.recommenders;

import com.example.recipenowwebappbackend.dtos.RecipeLiteDto;
import com.example.recipenowwebappbackend.dtos.UserDtoLite;
import com.example.recipenowwebappbackend.repositories.InteractionRepository;
import com.example.recipenowwebappbackend.repositories.RecipeRepository;
import com.example.recipenowwebappbackend.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@Service
public class RecommendationSystem {

    @Autowired
    private CollaborativeFilteringRecommender collaborativeFilteringRecommender;

    @Autowired
    private BayesRating bayesRating;
    private static final int NUM_NEIGHBOURHOODS = 30;
    private static final int NUM_RECOMMENDATIONS = 1000;
    private static final int MIN_VALUE_RECOMMENDATION = 4;


    public List<RecipeLiteDto> getRecommendedRecipes(Long userId, ConcurrentHashMap<Long, ConcurrentHashMap<Long, Integer>> userRatingsMap, ConcurrentHashMap<Long, Double> userAverageRatingMap, ConcurrentHashMap<Long, RecipeLiteDto> recipeLiteDtoMap, ConcurrentHashMap<Long, UserDtoLite> userDtoLiteMap) {
        List<RecipeLiteDto> recommendedRecipesDto;
        try {
            recommendedRecipesDto = collaborativeFilteringRecommender.recommendedRecipes(userId, userRatingsMap, userAverageRatingMap, recipeLiteDtoMap, userDtoLiteMap, NUM_RECOMMENDATIONS, NUM_NEIGHBOURHOODS, MIN_VALUE_RECOMMENDATION);
        } catch (Exception exception) {
            recommendedRecipesDto = new ArrayList<>();
        }
        return recommendedRecipesDto;
    }

    public List<RecipeLiteDto> getPopularRecipes( ConcurrentHashMap<Long, RecipeLiteDto> recipeLiteDtoMap){
        return bayesRating.calculateWeightedRatings(recipeLiteDtoMap);
    }

    public List<RecipeLiteDto> combineRecommendedAndPopular(Long userId, ConcurrentHashMap<Long, ConcurrentHashMap<Long, Integer>> userRatingsMap, ConcurrentHashMap<Long, Double> userAverageRatingMap, ConcurrentHashMap<Long, RecipeLiteDto> recipeLiteDtoMap, ConcurrentHashMap<Long, UserDtoLite> userDtoLiteMap) {
        List<RecipeLiteDto> recommendedRecipesDto = this.getRecommendedRecipes(userId,userRatingsMap,userAverageRatingMap,recipeLiteDtoMap,userDtoLiteMap);
        List<RecipeLiteDto> popularRecipesDto =  this.getPopularRecipes(recipeLiteDtoMap);

        Set<Long> combinedRecipeIds = new HashSet<>();
        List<RecipeLiteDto> combinedList = new ArrayList<>();
        Random random = new Random();

        int recommendedIndex = 0;
        int popularIndex = 0;

        while (recommendedIndex < recommendedRecipesDto.size() && popularIndex < popularRecipesDto.size()) {
            for (int i = 0; i < 3 && recommendedIndex < recommendedRecipesDto.size(); i++) {
                RecipeLiteDto recommendedRecipe = recommendedRecipesDto.get(recommendedIndex);
                if (combinedRecipeIds.add(recommendedRecipe.getId())) {
                    combinedList.add(recommendedRecipe);
                }
                recommendedIndex++;
            }

            if (popularIndex < popularRecipesDto.size()) {
                RecipeLiteDto popularRecipe = popularRecipesDto.get(popularIndex);
                if (combinedRecipeIds.add(popularRecipe.getId())) {
                    combinedList.add(popularRecipe);
                }
                popularIndex++;
            }

            Collections.shuffle(combinedList.subList(combinedList.size() - 4, combinedList.size()), random);
        }

        while (popularIndex < popularRecipesDto.size()) {
            RecipeLiteDto popularRecipe = popularRecipesDto.get(popularIndex);
            if (combinedRecipeIds.add(popularRecipe.getId())) {
                combinedList.add(popularRecipe);
            }
            popularIndex++;
        }
        while (recommendedIndex < recommendedRecipesDto.size()) {
            RecipeLiteDto recommendedRecipe = recommendedRecipesDto.get(recommendedIndex);
            if (combinedRecipeIds.add(recommendedRecipe.getId())) {
                combinedList.add(recommendedRecipe);
            }
            recommendedIndex++;
        }

        return combinedList;
    }



}
