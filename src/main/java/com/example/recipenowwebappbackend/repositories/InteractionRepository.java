package com.example.recipenowwebappbackend.repositories;

import com.example.recipenowwebappbackend.models.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository
public interface InteractionRepository extends PagingAndSortingRepository<Interaction, Long>, JpaRepository<Interaction, Long> {

        List<Interaction> findInteractionsByUserId(Long userId);

}
