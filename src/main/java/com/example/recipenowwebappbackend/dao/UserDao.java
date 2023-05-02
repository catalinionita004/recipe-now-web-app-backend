package com.example.recipenowwebappbackend.dao;


import com.example.recipenowwebappbackend.dao.base.BaseDao;
import com.example.recipenowwebappbackend.entity.User;
import com.example.recipenowwebappbackend.repository.UserRepository;

import java.util.Optional;


public interface UserDao extends BaseDao<User, UserRepository> {
    Optional<User> findUserByEmail(String email);

    Boolean isUserExistsByEmail(String email);
}
