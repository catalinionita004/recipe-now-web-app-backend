package com.example.recipenowwebappbackend.service;


import com.example.recipenowwebappbackend.dao.RecipeDao;
import com.example.recipenowwebappbackend.dto.RecipeDto;
import com.example.recipenowwebappbackend.transformer.RecipeTransformer;
import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service

public class RecipeServiceImpl implements RecipeService {
    private static final int MIN_OF_RECOMMENDED_BOOKS = 14;
    private final RecipeTransformer bookTransformer;
    private final RecipeDao bookDao;

    public RecipeServiceImpl(RecipeTransformer bookTransformer, RecipeDao bookDao) {
        this.bookTransformer = bookTransformer;
        this.bookDao = bookDao;
    }

    @Override
    public RecipeDao getDao() {
        return bookDao;
    }

    @Override
    public RecipeTransformer getTransformer() {
        return bookTransformer;
    }

    @Override
    public RecipeDto create(RecipeDto dto) {
        log.info("BookService: create() called");
        // set author entity before creating
        //dto.setAuthor(authorService.findById(dto.getAuthor().getId()));
        return getTransformer().transformEntityToDto(getDao().create(getTransformer().transformDtoToEntity(dto)));
    }

    /*

    @Override
    public RecipeDto update(RecipeDto dto, Long id) {
        log.info("BookService: update() called");
        Optional<RecipeDto> book = getDao().findById(id);
        if (book.isEmpty())
            throw new EntityExistsException("Book not found for id: " + id);

        // set author entity before updating
        dto.setAuthor(authorService.findById(dto.getAuthor().getId()));
        getTransformer().updateEntity(dto, book.get());
        return getTransformer().transformEntityToDto(getDao().update(book.get()));
    }

    @Override
    public List<BookDto> findAllBooksByAuthorId(Long authorId) {
        log.info("BookService: findAllBooksByAuthorId() called");
        return getTransformer().transformEntityToDto(getDao().findAllBooksByAuthorId(authorId));
    }

    @Override
    public PaginationResponse<BookDto> findAllBooksPaginatedAndFiltered(FilterPaginationRequest<BookFilterPaginationRequest> bookFilterPaginationRequest) {
        log.info("BookService: findAllBooksPaginatedAndFiltered() called");
        return buildPaginationResponse(getDao().findAllBooksPaginatedAndFiltered(bookFilterPaginationRequest));
    }

    @Override
    public List<BookDto> findAllRecommendedBooks() {
        log.info("BookService: findAllRecommendedBooks() called");
        return getTransformer().transformEntityToDto(getDao().findAllBooksByCategoriesAndLimit(
                userReadingInfoService.findUserReadingInfo().getUserBookCategories().stream().map(
                        userBookCategoryDto -> userBookCategoryDto.getCategory().getName()
                ).collect(Collectors.toList()), MIN_OF_RECOMMENDED_BOOKS));
    }
*/
    // free hosting cause leak algo speed for fetching
//    @Override
//    public List<BookDto> findAllRecommendedBooks() {
//        log.info("BookService: findAllRecommendedBooks() called");
//        UserDto currentUser = userService.getCurrentUser();
//        List<Book> books = collaborativeFilteringRecommender.recommendedBooks(currentUser.getId());
//        if (books.size() < MIN_OF_RECOMMENDED_BOOKS) {
//            books.addAll(getDao().findAllBooksByCategoriesAndLimit(
//                    userReadingInfoService.findUserReadingInfo().getUserBookCategories().stream().map(
//                    userBookCategoryDto -> userBookCategoryDto.getCategory().getName()
//            ).collect(Collectors.toList()), MIN_OF_RECOMMENDED_BOOKS - books.size()));
//        }
//        return getTransformer().transformEntityToDto(books);
//    }
}
