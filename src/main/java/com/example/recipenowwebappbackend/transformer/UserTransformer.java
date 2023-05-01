package com.example.recipenowwebappbackend.transformer;

import com.henry.bookrecommendationsystem.dto.UserDto;
import com.henry.bookrecommendationsystem.entity.User;
import com.henry.bookrecommendationsystem.transformer.base.BaseTransformer;
import com.henry.bookrecommendationsystem.transformer.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 04/11/2022
 */
@Component
@AllArgsConstructor
public class UserTransformer implements BaseTransformer<User, UserDto, UserMapper> {
    private final UserMapper userMapper;

    @Override
    public UserMapper getMapper() {
        return userMapper;
    }
}
