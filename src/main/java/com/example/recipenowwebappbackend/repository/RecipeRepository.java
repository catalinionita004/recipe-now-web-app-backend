package com.example.recipenowwebappbackend.repository;

import com.example.recipenowwebappbackend.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    /*
    List<Recipe> findAllByAuthorIdAndMarkedAsDeletedFalse(Long authorId);

    //@Query("SELECT b FROM Book b WHERE b.markedAsDeleted = :deletedRecords ")
    Page<Recipe> findAll(Pageable pageable, Boolean deletedRecords);

    List<Recipe> findAllByCategoryNameInAndMarkedAsDeletedFalse(List<String> categories);

//    @Query("SELECT b FROM Book b WHERE b.markedAsDeleted = :deletedRecords " +
//            "AND ((:name) IS NULL OR lower(b.name) like %:name%) " +
//            "AND ((:fromPrice) IS NULL OR b.price >= :fromPrice) " +
//            "AND ((:toPrice) IS NULL OR b.price <= :toPrice) " +
//            "AND ((:fromPagesNumber) IS NULL OR b.pagesNumber >= :fromPagesNumber) " +
//            "AND ((:toPagesNumber) IS NULL OR b.pagesNumber <= :toPagesNumber) " +
//            "AND ((:fromReadingDuration) IS NULL OR b.readingDuration >= :fromReadingDuration) " +
//            "AND ((:toReadingDuration) IS NULL OR b.readingDuration <= :toReadingDuration) " +
//            "AND ((:categories) IS NULL OR (b.category.name IN (:categories))) ")
    Page<Recipe> findAllBooksPaginatedAndFiltered(String name, Set<String> categories, Double fromPrice, Double toPrice, Integer fromPagesNumber, Integer toPagesNumber, Integer fromReadingDuration, Integer toReadingDuration, Boolean deletedRecords, Pageable pageable);
*/
}
