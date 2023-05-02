package com.example.recipenowwebappbackend.dao;

import com.example.recipenowwebappbackend.entity.Recipe;
import com.example.recipenowwebappbackend.repository.RecipeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RecipeDaoImpl implements RecipeDao {
    private final RecipeRepository bookRepository;

    public RecipeDaoImpl(RecipeRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public RecipeRepository getRepository() {
        return bookRepository;
    }

    /*
    @Override
    public List<Recipe> findAllBooksByAuthorId(Long authorId) {
        return getRepository().findAllByAuthorIdAndMarkedAsDeletedFalse(authorId);
    }

    @Override
    public List<Recipe> findAllBooksByCategoriesAndLimit(List<String> categories, Integer limit) {
        List<Recipe> books = getRepository().findAllByCategoryNameInAndMarkedAsDeletedFalse(categories);
        if (books.size() <= limit) return books;
        return books.subList(0, limit);
    }

    @Override
    public Page<Recipe> findAllBooksPaginatedAndFiltered(FilterPaginationRequest<BookFilterPaginationRequest> bookFilterPaginationRequest) {
        PageRequest pageRequest = getPageRequest(bookFilterPaginationRequest);
        BookFilterPaginationRequest criteria = bookFilterPaginationRequest.getCriteria();
        if (criteria == null)
            return getRepository().findAll(pageRequest, bookFilterPaginationRequest.getDeletedRecords());
        return getRepository().findAllBooksPaginatedAndFiltered(criteria.getName(), criteria.getCategories(),
                criteria.getFromPrice(), criteria.getToPrice(), criteria.getFromPagesNumber(), criteria.getToPagesNumber(),
                criteria.getFromReadingDuration(), criteria.getToReadingDuration(), bookFilterPaginationRequest.getDeletedRecords(), pageRequest);
    }

    private PageRequest getPageRequest(FilterPaginationRequest<BookFilterPaginationRequest> bookFilterPaginationRequest) {
        if (bookFilterPaginationRequest.getPageSize() == -1) bookFilterPaginationRequest.setPageSize(Integer.MAX_VALUE);
        return PageRequest.of(bookFilterPaginationRequest.getPageNumber() - 1, bookFilterPaginationRequest.getPageSize(), buildSort(bookFilterPaginationRequest, Book.class));
    }
    */
}
