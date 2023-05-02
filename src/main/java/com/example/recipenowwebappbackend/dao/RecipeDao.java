package com.example.recipenowwebappbackend.dao;


import com.example.recipenowwebappbackend.dao.base.BaseDao;
import com.example.recipenowwebappbackend.entity.Recipe;
import com.example.recipenowwebappbackend.repository.RecipeRepository;
import org.springframework.data.domain.Page;

import java.util.List;


public interface RecipeDao extends BaseDao<Recipe, RecipeRepository> {
    //List<Recipe> findAllBooksByAuthorId(Long authorId);

    //List<Recipe> findAllBooksByCategoriesAndLimit(List<String> categories, Integer limit);

    //Page<Recipe> findAllBooksPaginatedAndFiltered(FilterPaginationRequest<BookFilterPaginationRequest> bookFilterPaginationRequest);
}
