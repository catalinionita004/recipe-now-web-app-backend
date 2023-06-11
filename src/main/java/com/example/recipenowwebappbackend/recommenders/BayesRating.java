package com.example.recipenowwebappbackend.recommenders;

import com.example.recipenowwebappbackend.dtos.RecipeLiteDto;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class BayesRating {

    private ConcurrentHashMap<Long, RecipeLiteDto> recipeLiteDtoMap;


    public List<RecipeLiteDto> calculateWeightedRatings(ConcurrentHashMap<Long, RecipeLiteDto> recipeDtoMap) {
        this.recipeLiteDtoMap = recipeDtoMap;
        double averageGlobalRating = calculateAverageGlobalRating();
        double m = determineMValue();
        List<RecipeLiteDto> weightedRecipes = recipeLiteDtoMap.keySet().parallelStream()
                .map(id -> {
                    RecipeLiteDto recipe = new RecipeLiteDto(recipeLiteDtoMap.get(id));
                    double R = recipe.getAverageRating();
                    int v = recipe.getRatingCount();
                    double weightedRating = (v / (v + m)) * R + (m / (v + m)) * averageGlobalRating;
                    recipe.setPopularityScore(weightedRating);
                    recipe.setId(id);
                    return recipe;
                })
                .collect(Collectors.toList());
        weightedRecipes.sort(Comparator.comparing(RecipeLiteDto::getPopularityScore).reversed());
        return weightedRecipes;
    }

    private double calculateAverageGlobalRating() {
        double sum = recipeLiteDtoMap.values().stream()
                .mapToDouble(recipe -> recipe.getAverageRating() * recipe.getRatingCount())
                .sum();
        int count = recipeLiteDtoMap.values().stream().mapToInt(RecipeLiteDto::getRatingCount).sum();
        return count > 0 ? sum / count : 0;
    }

    private double determineMValue() {
        double percentile = 0.9;
        int[] sortedVotes = recipeLiteDtoMap.values().stream()
                .mapToInt(RecipeLiteDto::getRatingCount)
                .sorted()
                .toArray();
        int index = (int) Math.ceil(percentile * recipeLiteDtoMap.size());
        return sortedVotes[index];
    }
}
