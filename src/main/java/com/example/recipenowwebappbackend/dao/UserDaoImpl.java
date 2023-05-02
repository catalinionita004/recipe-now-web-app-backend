package com.example.recipenowwebappbackend.dao;

import com.example.recipenowwebappbackend.entity.User;
import com.example.recipenowwebappbackend.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserRepository getRepository() {
        return userRepository;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return getRepository().findByEmail(email);
    }

    @Override
    public Boolean isUserExistsByEmail(String email) {
        return getRepository().existsByEmail(email);
    }
}
