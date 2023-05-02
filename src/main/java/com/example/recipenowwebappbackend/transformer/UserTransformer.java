package com.example.recipenowwebappbackend.transformer;


import com.example.recipenowwebappbackend.dto.UserDto;
import com.example.recipenowwebappbackend.entity.User;
import com.example.recipenowwebappbackend.transformer.base.BaseTransformer;
import com.example.recipenowwebappbackend.transformer.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class UserTransformer implements BaseTransformer<User, UserDto, UserMapper> {
    private final UserMapper userMapper;

    @Override
    public UserMapper getMapper() {
        return userMapper;
    }
}
