package com.example.recipenowwebappbackend.services;

import com.example.recipenowwebappbackend.dtos.*;
import com.example.recipenowwebappbackend.exceptions.ResourceNotFoundException;
import com.example.recipenowwebappbackend.mappers.RecipeMapper;
import com.example.recipenowwebappbackend.mappers.UserMapper;
import com.example.recipenowwebappbackend.models.*;
import com.example.recipenowwebappbackend.recommenders.RecommendationSystem;
import com.example.recipenowwebappbackend.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RecipeMapper recipeMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RecommendationSystem recommendationSystem;


    @Autowired
    private InteractionRepository interactionRepository;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;
    ConcurrentHashMap<Long, ConcurrentHashMap<Long, Integer>> userRatingsMap = new ConcurrentHashMap<>();
    ConcurrentHashMap<Long, Double> userAverageRatingMap = new ConcurrentHashMap<>();

    ConcurrentHashMap<Long, UserDtoLite> userDtoLiteMap = new ConcurrentHashMap<>();


    ConcurrentHashMap<Long, RecipeLiteDto> recipeLiteDtoMap = new ConcurrentHashMap<>();


    boolean populated = false;


    @PostConstruct
    public void populateAllMaps() {
        if (!populated) {
            populateUserRatingsMapFromDb();
            populateUserAverageRatingMapFromDb();
            populateRecipeLiteDtoMapFromDb();
            populateUserDtoLiteMap();
            populated = true;
        }

    }

    private void populateUserRatingsMapFromDb() {
        log.info("CollaborativeFilteringRecommender: populateUserRatingsMapFromDb() called)");
        List<Object[]> results = interactionRepository.findUserRecipeRatings();
        results.parallelStream().forEach(result -> {
            Long userId = ((Number) result[0]).longValue();
            Long recipeId = ((Number) result[1]).longValue();
            Integer rating = (Integer) result[2];
            userRatingsMap.computeIfAbsent(userId, k -> new ConcurrentHashMap<>()).put(recipeId, rating);
        });
    }

    private void populateUserAverageRatingMapFromDb() {
        log.info("CollaborativeFilteringRecommender: populateUserAverageRatingMapFromDb() called)");
        List<Object[]> results = interactionRepository.findUserAverageRatings();
        results.parallelStream().forEach(result -> {
            Long userId = ((Number) result[0]).longValue();
            Double averageRatingValue = ((BigDecimal) result[1]).doubleValue();
            userAverageRatingMap.put(userId, averageRatingValue);
        });
    }

    private void populateUserDtoLiteMap() {
        log.info("CollaborativeFilteringRecommender: populateUserDtoLiteMap() called)");
        List<Object[]> results = userRepository.findUsersDtoLite();
        results.parallelStream().forEach(result -> {
            Long userId = ((Number) result[0]).longValue();
            String firstName = result[1].toString();
            String lastName = result[2].toString();
            userDtoLiteMap.put(userId, new UserDtoLite(userId, firstName, lastName));
        });

    }


    private void populateRecipeLiteDtoMapFromDb() {
        log.info("CollaborativeFilteringRecommender: populateRecipeLiteDtoMapFromDb() called)");
        List<Object[]> results = recipeRepository.findRecipesLite();
        results.parallelStream().forEach(result -> {
            Long recipeId = ((Number) result[0]).longValue();
            String recipeName = ((String) result[1]);
            Double averageRating = (Double) result[2];
            int ratingCounter = ((Number) result[3]).intValue();
            int minutes = ((Number) result[4]).intValue();
            LocalDateTime submitted = (LocalDateTime) result[5];
            LocalDateTime editDate = (LocalDateTime) result[6];
            String imageUrl = (String) result[7];
            UserDto userDto = userMapper.modelToDto((User) result[8]);

            recipeLiteDtoMap.computeIfAbsent(recipeId, k -> new RecipeLiteDto(recipeId, recipeName, averageRating, ratingCounter, minutes, userDto, submitted, editDate, imageUrl));
        });
    }

    private List<RecipeLiteDto> findReviewedRecipesByUser(Long userId) {
        List<Object[]> results = recipeRepository.findReviewedRecipesLiteByUserId(userId);

        return results.parallelStream().map(result -> {
            Long recipeId = ((Number) result[0]).longValue();
            String recipeName = ((String) result[1]);
            Double averageRating = (Double) result[2];
            int ratingCounter = ((Number) result[3]).intValue();
            int minutes = ((Number) result[4]).intValue();
            LocalDateTime submitted = (LocalDateTime) result[5];
            LocalDateTime editDate = (LocalDateTime) result[6];
            String imageUrl = (String) result[7];

            return new RecipeLiteDto(recipeId, recipeName, averageRating, ratingCounter, minutes, null, submitted, editDate, imageUrl);
        }).sorted((recipe1, recipe2) -> {
            LocalDateTime date1 = (recipe1.getSubmitted() != null) ? recipe1.getSubmitted() : recipe1.getEditDate();
            LocalDateTime date2 = (recipe2.getSubmitted() != null) ? recipe2.getSubmitted() : recipe2.getEditDate();

            if (date1 == null && date2 == null) {
                return 0;
            } else if (date1 == null) {
                return 1;
            } else if (date2 == null) {
                return -1;
            } else {
                return date2.compareTo(date1); // sortare descrescatoare
            }
        }).collect(Collectors.toList());
    }


    public RecipeDto findRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Recipe not found with id " + id));

        Set<Interaction> sortedInteractions = recipe.getInteractions().stream()
                .sorted((i1, i2) -> {
                    LocalDateTime d1 = i1.getEditDate() != null ? i1.getEditDate() : i1.getSubmitted();
                    LocalDateTime d2 = i2.getEditDate() != null ? i2.getEditDate() : i2.getSubmitted();
                    return d2.compareTo(d1);
                }).collect(Collectors.toCollection(LinkedHashSet::new));

        recipe.setInteractions(sortedInteractions);

        return recipeMapper.modelToDto(recipe);
    }


    public Page<RecipeLiteDto> findAllRecommendedRecipes(Pageable page) {
        UserDto currentUser = userService.getCurrentUser();
        List<RecipeLiteDto> recommendedRecipes = recommendationSystem.combineRecommendedAndPopular(currentUser.getId(), userRatingsMap, userAverageRatingMap, recipeLiteDtoMap, userDtoLiteMap);
        if (page.getPageNumber() * page.getPageSize() > recommendedRecipes.size())
            return new PageImpl<>(new ArrayList<>());
        int start = (int) page.getOffset();
        int end = Math.min((start + page.getPageSize()), recommendedRecipes.size());
        return new PageImpl<>(recommendedRecipes.subList(start, end), page, recommendedRecipes.size());
    }


    public Page<RecipeLiteDto> findReviewedRecipes(Pageable page) {
        UserDto currentUser = userService.getCurrentUser();
        List<RecipeLiteDto> reviewedRecipes = findReviewedRecipesByUser(currentUser.getId());
        if (page.getPageNumber() * page.getPageSize() > reviewedRecipes.size())
            return new PageImpl<>(new ArrayList<>());
        int start = (int) page.getOffset();
        int end = Math.min((start + page.getPageSize()), reviewedRecipes.size());
        return new PageImpl<>(reviewedRecipes.subList(start, end), page, reviewedRecipes.size());
    }


    public RecipeDto saveRecipe(RecipeDto recipeDto) {
        System.out.println("se intra sa se salveze");
        UserDto currentUserDto = userService.getCurrentUser();
        recipeDto.setUser(currentUserDto);

        LocalDateTime localDateTime = LocalDateTime.now();
        if (recipeDto.getId() != null && recipeRepository.existsById(recipeDto.getId()))
            recipeDto.setEditDate(localDateTime);
        else {
            recipeDto.setSubmitted(localDateTime);
            recipeDto.setEditDate(localDateTime);
        }
        RecipeDto savedRecipeDto = recipeMapper.modelToDto(recipeRepository.save(recipeMapper.dtoToModel(recipeDto)));

        List<Object[]> results = recipeRepository.findAverageCountRatingByRecipeId(savedRecipeDto.getId());
        RecipeLiteDto newRecipeLiteDto = new RecipeLiteDto(savedRecipeDto);
        newRecipeLiteDto.setAverageRating(results.get(0)[0] !=null ? (Double) results.get(0)[0] : 0.0d);
        newRecipeLiteDto.setRatingCount(((Number) results.get(0)[1]).intValue());
        recipeLiteDtoMap.put(savedRecipeDto.getId(), newRecipeLiteDto);

        System.out.println("am salvat reteta");
        return savedRecipeDto;
    }

    public RecipeDto deleteRecipe(Long id) {
        RecipeDto recipeDto;
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            recipeDto = recipeMapper.modelToDto(recipe);
            recipe.setIngredients(new HashSet<>());
            recipe.setTags(new HashSet<>());
            recipeRepository.save(recipe);
            recipeRepository.deleteById(id);
            recipeLiteDtoMap.remove(id);
            return recipeDto;
        }
        throw new ResourceNotFoundException("Recipe not found with id " + id);
    }


    public List<RecipeLiteDto> findAllRecipesByUser(Long userId) {
        List<Object[]> results = recipeRepository.findRecipesLiteByUser(userId);
        List<RecipeLiteDto> recipeLiteDtos = new ArrayList<>();
        UserDto userDto = userService.findById(userId);
        results.parallelStream().forEach(result -> {
            Long recipeId = ((Number) result[0]).longValue();
            String recipeName = ((String) result[1]);
            Double averageRating = (Double) result[2];
            int ratingCounter = ((Number) result[3]).intValue();
            int minutes = ((Number) result[4]).intValue();
            LocalDateTime submittedDate = (LocalDateTime) result[5];
            LocalDateTime editDate = (LocalDateTime) result[6];
            String imageUrl = (String) result[7];

            recipeLiteDtos.add(new RecipeLiteDto(recipeId, recipeName, averageRating, ratingCounter, minutes, userDto, submittedDate, editDate, imageUrl));
        });

        recipeLiteDtos.sort((recipe1, recipe2) -> {
            LocalDateTime date1 = (recipe1.getSubmitted() != null) ? recipe1.getSubmitted() : recipe1.getEditDate();
            LocalDateTime date2 = (recipe2.getSubmitted() != null) ? recipe2.getSubmitted() : recipe2.getEditDate();

            if (date1 == null && date2 == null) {
                return 0;
            } else if (date1 == null) {
                return 1;
            } else if (date2 == null) {
                return -1;
            } else {
                return date2.compareTo(date1); // sortare descrescatoare
            }
        });

        return recipeLiteDtos;
    }

    public Page<RecipeLiteDto> searchRecipes(String name, Integer minMinutes, Integer maxMinutes, List<Long> ingredients, List<Long> tags, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RecipeSearchDto> query = cb.createQuery(RecipeSearchDto.class);
        Root<Recipe> root = query.from(Recipe.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null) {
            String[] searchTerms = name.toUpperCase().split("\\s+");
            List<Predicate> namePredicates = new ArrayList<>();
            for (String term : searchTerms) {
                namePredicates.add(cb.like(cb.upper(root.get("name")), "%" + term + "%"));
            }
            predicates.add(cb.and(namePredicates.toArray(new Predicate[0])));
        }


        if (minMinutes != null && maxMinutes != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("minutes"), minMinutes));
            predicates.add(cb.lessThanOrEqualTo(root.get("minutes"), maxMinutes));
        }

        if (ingredients != null && !ingredients.isEmpty()) {
            Subquery<Long> ingredientSubquery = query.subquery(Long.class);
            Root<Recipe> subRoot = ingredientSubquery.from(Recipe.class);
            Join<Recipe, Ingredient> ingredientJoin = subRoot.join("ingredients");
            ingredientSubquery.select(subRoot.get("id"))
                    .where(ingredientJoin.get("id").in(ingredients));
            ingredientSubquery.groupBy(subRoot.get("id"));
            ingredientSubquery.having(cb.equal(cb.count(subRoot.get("id")), ingredients.size()));
            predicates.add(cb.in(root.get("id")).value(ingredientSubquery));
        }


        if (tags != null && !tags.isEmpty()) {
            Subquery<Long> tagSubquery = query.subquery(Long.class);
            Root<Recipe> subRoot = tagSubquery.from(Recipe.class);
            Join<Recipe, Tag> tagJoin = subRoot.join("tags");
            tagSubquery.select(subRoot.get("id"))
                    .where(tagJoin.get("id").in(tags));
            tagSubquery.groupBy(subRoot.get("id"));
            tagSubquery.having(cb.equal(cb.count(subRoot.get("id")), tags.size()));
            predicates.add(cb.in(root.get("id")).value(tagSubquery));
        }


        query.select(cb.construct(RecipeSearchDto.class, root.get("id"), root.get("name"), root.get("submitted")))
                .distinct(true)
                .where(predicates.toArray(new Predicate[0]));

        if (pageable != null) {
            List<Order> orders = new ArrayList<>();
            if (pageable.getSort().isSorted()) {
                pageable.getSort().forEach(order -> {
                    String property = order.getProperty();
                    if (order.getDirection() == Sort.Direction.ASC) {
                        orders.add(cb.asc(root.get(property)));
                    } else {
                        orders.add(cb.desc(root.get(property)));
                    }
                    System.out.println("Sort property: " + property + ", Direction: " + order.getDirection());
                });
            } else {
                // Sortare implicită după nume (name) în ordine ascendentă
                orders.add(cb.asc(root.get("name")));
                System.out.println("Sort property: name, Direction: ASC");
            }
            query.orderBy(orders);
        }

        TypedQuery<RecipeSearchDto> typedQuery = entityManager.createQuery(query);
        long totalCount = typedQuery.getResultList().size();
        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<RecipeLiteDto> recipeLiteDtos = typedQuery.getResultList().stream().map(recipeSearchDto -> recipeLiteDtoMap.get(recipeSearchDto.getId())).collect(Collectors.toList());
        return new PageImpl<>(recipeLiteDtos, pageable, totalCount);
    }


}

