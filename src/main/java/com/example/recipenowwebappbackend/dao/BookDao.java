package com.example.recipenowwebappbackend.dao;

import com.henry.bookrecommendationsystem.dao.base.BaseDao;
import com.henry.bookrecommendationsystem.dto.BookFilterPaginationRequest;
import com.henry.bookrecommendationsystem.dto.base.pagination.FilterPaginationRequest;
import com.henry.bookrecommendationsystem.entity.Book;
import com.henry.bookrecommendationsystem.repository.BookRepository;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Henry Azer
 * @since 07/11/2022
 */
public interface BookDao extends BaseDao<Book, BookRepository> {
    List<Book> findAllBooksByAuthorId(Long authorId);

    List<Book> findAllBooksByCategoriesAndLimit(List<String> categories, Integer limit);

    Page<Book> findAllBooksPaginatedAndFiltered(FilterPaginationRequest<BookFilterPaginationRequest> bookFilterPaginationRequest);
}
