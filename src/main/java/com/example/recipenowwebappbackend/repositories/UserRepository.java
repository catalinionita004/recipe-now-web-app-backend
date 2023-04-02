package com.example.recipenowwebappbackend.repositories;


import com.example.recipenowwebappbackend.models.Role;
import com.example.recipenowwebappbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = 1 WHERE a.username = :username")
    void enableUser(@Param("username") String username);

    @Modifying
    @Query("UPDATE User a " +
            "SET a.role = :role WHERE a.username = :username ")
    void updateUserRole(@Param("username") String username, @Param("role") Role role);

    List<User> findAll();
}
