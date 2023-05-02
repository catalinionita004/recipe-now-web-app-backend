package com.example.recipenowwebappbackend.service;



import com.example.recipenowwebappbackend.dao.RecipeDao;
import com.example.recipenowwebappbackend.dto.BookFilterPaginationRequest;
import com.example.recipenowwebappbackend.dto.RecipeDto;
import com.example.recipenowwebappbackend.dto.base.pagination.FilterPaginationRequest;
import com.example.recipenowwebappbackend.dto.base.response.PaginationResponse;
import com.example.recipenowwebappbackend.entity.Recipe;
import com.example.recipenowwebappbackend.service.base.BaseService;
import com.example.recipenowwebappbackend.transformer.RecipeTransformer;

import java.util.List;

public interface RecipeService extends BaseService<Recipe, RecipeDto, RecipeDao, RecipeTransformer> {
//    List<RecipeDto> findAllBooksByAuthorId(Long authorId);
//
//    PaginationResponse<RecipeDto> findAllBooksPaginatedAndFiltered(FilterPaginationRequest<BookFilterPaginationRequest> bookFilterPaginationRequest);
//
//    List<RecipeDto> findAllRecommendedBooks();
}
